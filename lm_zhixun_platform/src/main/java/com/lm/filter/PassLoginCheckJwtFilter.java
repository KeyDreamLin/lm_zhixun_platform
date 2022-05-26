package com.lm.filter;

import com.lm.common.ex.handler.UserExceptionHandler;
import com.lm.common.r.UserResultEnum;
import com.lm.pojo.User;
import com.lm.service.user.UserService;
import com.lm.service.user.UserThreadLocal;
import com.lm.tool.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检查用户权限，是否登录
 * jwt
 */
@Component
@Slf4j
public class PassLoginCheckJwtFilter implements HandlerInterceptor {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;
    //用于在将请求发送到控制器之前执行操作。此方法应返回true，以将响应返回给客户端。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //检查token是否合法
        if (!StringUtils.hasLength(token)){
            throw new UserExceptionHandler(UserResultEnum.USER_TOKEN_ERROR);
        }
        //校验token是否失效
        boolean verify = jwtService.verify(token);
        if (verify == false){
            //如果校验失败，抛出token过期了，前台就会捕获这个状态，会跳转到登录页面，重新登录获取token
            throw new UserExceptionHandler(UserResultEnum.USER_TOKEN_NOT_FOUND);
        }
        //解析token，获取用户id
        Long userId = jwtService.getTokenUserId(token);
        if (userId == null){
            //用户不存在
            throw new UserExceptionHandler(UserResultEnum.USER_NULL_ERROR);
        }
        //根据用户id查询用户信息
        User user = userService.getById(userId);
        if (user==null){
            throw new UserExceptionHandler(UserResultEnum.USER_NULL_ERROR);
        }
        //把用户信息放入UserThreadLocal
        UserThreadLocal.put(user);
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserThreadLocal.remove();

    }

    //用完记得删除

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
