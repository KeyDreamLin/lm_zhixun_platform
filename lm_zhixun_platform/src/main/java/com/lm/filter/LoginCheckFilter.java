package com.lm.filter;

import com.lm.common.Constants;
import com.lm.common.ex.handler.UserExceptionHandler;
import com.lm.common.r.UserResultEnum;
import com.lm.service.usertoken.UserTokenService;
import com.lm.tool.Base64Tool;
import com.lm.tool.CookieTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检查用户权限，是否登录
 */
@Component
@Slf4j
public class LoginCheckFilter  implements HandlerInterceptor {
    @Autowired
    private UserTokenService userTokenService;


    //路径匹配器
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    //不拦截处理的路径
    private final String[] PathPass_S = new String[]{
        "/user/login",
            "/jwt/**"
    };



    //用于在将请求发送到控制器之前执行操作。此方法应返回true，以将响应返回给客户端。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //明天重构一下拦截器的代码，
        //在编写一个cookie的工具类！！！！！！！！！
        //1、先使用路径匹配器，将匹配到的路径放行，匹配不到的这进行下一部的处理
        //2、检查cookie是否为空，空这拒绝访问（未登录），返回登录页面
        //3、cookie的token base64的解密，然后分割字符串 ，若出现数组不全则拒绝访问
        //4、转换token解密出来的时间戳~~转换Long出现异常拒接访问并删除前端的cookie
        //5、当前时间戳和创建token的时间戳相减 如果大于Constants.FAILURE_TIMER_MS(30分钟的ms)这秘钥过期，小于则放行
        //需要前端传过来的token与数据库中的校对一下吗？

        //（过几天在改改token~token加上tokenId&UserID&创建时间戳）
        //过几天有空可以与数据库校对一下，防止篡改哈哈哈
        //获取html路径
        String html_path = request.getServletPath();
        log.info("拦截器拦截到path---> {}",html_path);
        if (PathCheck(html_path) == true){
            log.info("拦截器拦截到path--->{} 处理结果---> 不需要拦截处理的页面直接放行",html_path);
            return true;//匹配到路径直接放行
        }
        //需要拦截的页面

        //获取到cookie数组
        Cookie[] cookies = request.getCookies();
        //根据名称提取对应的cookie
        Cookie html_cookie = CookieTool.GetCookieByName(cookies, Constants.USER_TOKEN);

        if (html_cookie == null){
            //如果cookie等于空的时候，这代表未登录
//            response.getWriter().write(JSONObject.toJSONString(UserResultEnum.USER_NO_LOGIN));//回传未登录
            log.info("拦截器拦截到path---> {} 处理结果---> cookie为空拒绝访问~已返回未登录",html_path);
            throw new UserExceptionHandler(UserResultEnum.USER_NO_LOGIN);
//            return false;
        }

        //如果cookie存在，则继续处理cookie，解密--判断时间戳

        //获取cookie的值顺便解密
        String cookieValue =Base64Tool.Decoder(html_cookie.getValue());
        String[] token_arr = cookieValue.split("&");

        if (token_arr.length < 0){
            //cookie 解密后 小于1则表示token不全，就当是错误数据重新登录呗
            CookieTool.DeleteCookie(response,Constants.USER_TOKEN);//删除token
//            response.getWriter().write(JSONObject.toJSONString(UserResultEnum.USER_NO_LOGIN));//回传未登录
            log.info("拦截器拦截到path---> {} 处理结果---> cookie为空拒绝访问~已返回未登录",html_path);
            throw new UserExceptionHandler(UserResultEnum.USER_NO_LOGIN);
//            return false;
        }
        long token_timerTamp ;
        try {
            token_timerTamp = Long.valueOf(token_arr[1]);
        }catch (Exception e){
            //cookie 解密后 token接收异常时间戳转换Long失败，就当是错误数据重新登录呗
            CookieTool.DeleteCookie(response,Constants.USER_TOKEN);//删除token
//            response.getWriter().write(JSONObject.toJSONString(UserResultEnum.USER_NO_LOGIN));//回传未登录
            log.info("拦截器拦截到path---> {} 处理结果---> token接收异常时间戳转换Long失败~已返回未登录",html_path);
            throw new UserExceptionHandler(UserResultEnum.USER_NO_LOGIN);
//            return false;
        }
        Long this_timerTamp = System.currentTimeMillis();
        //当前时间戳减去token的创建的事件戳，大于30分钟，这token过期 这里是两个时间戳相减
        if ((this_timerTamp - token_timerTamp) > Constants.FAILURE_TIMER_MS){
            //token过期,顺便把浏览器的删除
            CookieTool.DeleteCookie(response,Constants.USER_TOKEN);
//            response.getWriter().write(JSONObject.toJSONString(UserResultEnum.USER_NO_LOGIN));//回传未登录
            log.info("拦截器拦截到path---> {} 处理结果--->token过期~已返回未登录",html_path);
            throw new UserExceptionHandler(UserResultEnum.USER_NO_LOGIN);
//            return false;
        }

        log.info("拦截器拦截到path---> {} 处理结果--->token未过期正常使用，ID为 {} 的用户的登录token还有{}分钟过期",
                    html_path,
                    token_arr[0],
                    (Constants.FAILURE_TIMER_M - ((this_timerTamp- token_timerTamp)/60/1000)));
        return true;
    }

    /**
     * 路径匹配
     * @param HttpPath
     * @return true 匹配放行，false不匹配的都不放行
     */
    private boolean PathCheck(String HttpPath) {
        for (String path_pass : PathPass_S) {
            return  antPathMatcher.match(path_pass, HttpPath);//匹配这返回true
        }
        return false;
    }
}
