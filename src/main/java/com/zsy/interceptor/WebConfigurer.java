package com.zsy.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname WebConfigurer
 * @Description  和springmvc的webmvc拦截配置一样
 * @Date 2020/2/26 19:58
 * @Created by hero
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }
}
