package com.lm.tool;

import com.lm.common.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieTool {

    /**
     * 通过cookie的名称获取Cookie对象
     * @param cookies cookie数组
     * @param cookieName cookie名称
     * @return
     */
    public static Cookie GetCookieByName(Cookie[] cookies,String cookieName){
        if (cookies == null){return null;}
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)){
                return cookie;
            }
        }
        return null;
    }

    /**
     * 删除浏览器中的cookie
     * @param response
     * @param cookieName cookie的值
     * @return 无含义
     */
    public static boolean DeleteCookie(HttpServletResponse response,String cookieName){
        Cookie cookie_re = new Cookie(cookieName,null);
        cookie_re.setMaxAge(0);
        cookie_re.setPath("/");
        response.addCookie(cookie_re);
        cookie_re = null;
        return true;
    }

    /**
     * 将cookie传到浏览器
     * @param response
     * @param cookieName cookie名称
     * @param cookieValue cookie的值
     * @return 无含义
     */
    private static boolean AddCookie(HttpServletResponse response,String cookieName,String cookieValue){
        // 创建一个 cookie对象
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");

        //设置过期时间
        cookie.setMaxAge(Constants.FAILURE_TIMER_S);

        //将cookie对象加入response响应
        response.addCookie(cookie);
        cookie = null;
        return true;
    }
}
