package com.linkbit.beidou;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       /* registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/back/portal/index");
        registry.addInterceptor(new UserSecurityInterceptor()).excludePathPatterns("/back/logout");
        registry.addInterceptor(new Interceptor2()).excludePathPatterns("/back/login");*/
        super.addInterceptors(registry);
    }

}