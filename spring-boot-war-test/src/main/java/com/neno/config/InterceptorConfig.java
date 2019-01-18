package com.neno.config;


import com.neno.interceptor.LogCostInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: root
 * @Date: 2019/1/14 17:51
 */
@Configuration
public class InterceptorConfig {

    @Bean
    public WebMvcConfigurer interceptorsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LogCostInterceptor()).addPathPatterns("/**");
            }
        };
    }

}
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogCostInterceptor()).addPathPatterns("/**");
//    }