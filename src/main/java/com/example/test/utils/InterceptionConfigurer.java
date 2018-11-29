package com.example.test.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class InterceptionConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    AuthInterceptor noaAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noaAuthInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/getLogin.do")
                .excludePathPatterns("/**")
                .excludePathPatterns("swagger-ui.html")
                .excludePathPatterns("/error")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/swagger-resources/**")
                .addPathPatterns("/webjars/springfox-swagger-ui");
        super.addInterceptors(registry);
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


}
