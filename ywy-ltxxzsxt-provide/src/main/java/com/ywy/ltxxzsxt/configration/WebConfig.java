package com.ywy.ltxxzsxt.configration;

/**
 * @description: TODO
 * @author: ywy
 * @date: 20210119
 * @version: 1.0.0
 */

import com.ywy.ltxxzsxt.interceptors.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor ;

    /**
     * 配置静态资源映射
     *
     * @param registry
     */

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) tokenInterceptor)
                .addPathPatterns("/lt/**")
                .excludePathPatterns("/lt/login");
    }
}