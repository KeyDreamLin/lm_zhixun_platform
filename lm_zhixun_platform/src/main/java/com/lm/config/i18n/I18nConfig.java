package com.lm.config.i18n;

import org.passay.MessageResolver;
import org.passay.spring.SpringMessageResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * 国际化配置类
 */
@Configuration
public class I18nConfig {
    /**
     * 默认解析器 其中locale表示默认语言
     * http://127.0.0.1:8877/admin/banner/saveupdate?lang=en_US
     * http://127.0.0.1:8877/admin/banner/saveupdate?lang=zh_CN
     *
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
    /**
     * 默认拦截器 用来设置切换预言的参数为 lang
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * 配置果酒湖文件的路径
     * @return
     */
    @Bean
    public MessageSource messageSource()  {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setDefaultEncoding("UTF-8");
        rbms.setBasenames("i18n/messages");
        return rbms;
    }

    /**
     * 配置 Java Validation 使用国际化的消息资源
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    /**
     * 配置 Passay 使用 Spring 的 MessageSource
     * @return MessageResolver
     */
    @Bean
    public MessageResolver messageResolver() {
        return new SpringMessageResolver(messageSource());
    }
}
