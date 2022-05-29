package com.lm.config;

import com.lm.filter.userlogin.PassLoginCheckJwtFilter;
import com.lm.filter.userlogin.PassportLogoutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private PassLoginCheckJwtFilter passLoginCheckJwtFilter;
    @Autowired
    private PassportLogoutInterceptor passportLogoutInterceptor;

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 先检查用户UUID是否过期
        registry.addInterceptor(passportLogoutInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login");
        // 再检查用户的jwt是否过期
        registry.addInterceptor(passLoginCheckJwtFilter).addPathPatterns("/user/**").excludePathPatterns("/user/login");
    }



    /***
     * 设置静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/login-pc-demo/**").addResourceLocations("classpath:/login-pc-demo/");

    }
}
