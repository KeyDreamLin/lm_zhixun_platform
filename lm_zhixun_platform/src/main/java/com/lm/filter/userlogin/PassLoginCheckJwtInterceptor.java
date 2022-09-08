package com.lm.filter.userlogin;

import com.lm.common.anno.IgnoreToken;
import com.lm.common.ex.lthrow.UserExceptionThrow;
import com.lm.common.r.UserResultEnum;
import com.lm.config.redis.JwtBlackSetService;
import com.lm.config.redis.key.RedisAndHeaderKey;
import com.lm.entity.pojo.adminuser.AdminUser;
import com.lm.service.adminuser.IAdminUserService;
import com.lm.service.adminmenu.AdminUserThreadLocal;
import com.lm.tool.DateTool;
import com.lm.tool.JwtService;
import com.lm.tool.LmAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 检查用户权限，是否登录
 * jwt
 */
@Component
@Slf4j
public class PassLoginCheckJwtInterceptor implements HandlerInterceptor , RedisAndHeaderKey {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IAdminUserService adminUserService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JwtBlackSetService jwtBlackSetService;

    //用于在将请求发送到控制器之前执行操作。此方法应返回true，以将响应返回给客户端。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //通过自定义注解去跳过token校验
        // handler从object对象转换成具体的目标对象HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取执行的方法
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(IgnoreToken.class) != null ||
                handlerMethod.getBeanType().getAnnotation(IgnoreToken.class) != null) {
            return true;
        }

        String token_jj = request.getHeader(HEADER_TOKEN_JJ);
        //检查token是否合法
        if (!StringUtils.hasLength(token_jj)){
            throw new UserExceptionThrow(UserResultEnum.USER_TOKEN_ERROR);
        }
        //校验token是否失效
        boolean verify = jwtService.verify(token_jj);
        if (verify == false){
            //如果校验失败，抛出token过期了，前台就会捕获这个状态，会跳转到登录页面，重新登录获取token
            throw new UserExceptionThrow(UserResultEnum.USER_TOKEN_NOT_FOUND);
        }

        // 校验jwt是否被拉黑（用户手动注销）
        // 返回true时jwt存在黑名单中
        boolean isBlack = jwtBlackSetService.isBlackList(token_jj);;
        log.info("校验jwt是否被拉黑-->{}",isBlack);
        // 返回token过期吧
        LmAssert.isTrueEx(isBlack,UserResultEnum.USER_TOKEN_NOT_FOUND);

        //解析token，获取用户id
        Long userId = jwtService.getTokenUserId(token_jj);
        if (userId == null){
            //用户不存在
            throw new UserExceptionThrow(UserResultEnum.USER_NULL_ERROR);
        }
        //根据用户id查询用户信息MySQL
        AdminUser user = adminUserService.getById(userId);
        if (user==null){
            throw new UserExceptionThrow(UserResultEnum.USER_NULL_ERROR);
        }

        // 对jwt续期，采用签发时间续期
        // token续期
        // 获取请求头的token_user_id
        String token_user_id = request.getHeader(HEADER_TOKEN_USER_ID);
        LmAssert.isNotNull(token_user_id,UserResultEnum.USER_TOKEN_ERROR);

        // 获取token jwt的签发时间
        Date signTokenTime = jwtService.getTokenIssuedTime(token_jj);
        // 获取token使用时间
        Long UseTokenDateMinute = DateTool.diffReturnMinute(new Date(),signTokenTime);
        log.info("token jwt的使用时间--->{}",UseTokenDateMinute);
        //使用时间超过了15分钟，就重新生成一串jwt返回给前端
        if (UseTokenDateMinute >= 15){
            // 续期，重新生成一个新的token
            String newToken = jwtService.createToken(Long.valueOf(token_user_id));
            log.info("重新生成一个jwt--->{}",newToken);

            // 通过response的头部输出token,然后前台通过reponse获取
            response.setHeader("token_jj", newToken);
        }

        //把用户信息放入UserThreadLocal
        AdminUserThreadLocal.put(user);
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AdminUserThreadLocal.remove();

    }

    //用完记得删除

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AdminUserThreadLocal.remove();
    }

}
