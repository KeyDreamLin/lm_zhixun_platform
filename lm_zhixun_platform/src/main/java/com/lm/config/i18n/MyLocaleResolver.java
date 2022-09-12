package com.lm.config.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
@Slf4j
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
      	// 从 request 域中读取传过来的参数
        String l = request.getHeader("lang");
        if(StringUtils.isEmpty(l)){
            l = request.getParameter("lang");
        }
        // 声明 Locale 为默认语言显示
        Locale locale = Locale.getDefault();
      	// 判断传入参数是否为空
        if (!StringUtils.isEmpty(l)){
          	// 将传过来的参数，通过下划线分割，获取到地区(zh)即代码(CN)
            String[] split = l.split("_");
          	// 进行赋值
            locale = new Locale(split[0],split[1]);
        }
        // 重新放入
        LocaleContextHolder.setLocale(locale);
      	// 返回
        return locale;
    }
 
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
 
    }
}