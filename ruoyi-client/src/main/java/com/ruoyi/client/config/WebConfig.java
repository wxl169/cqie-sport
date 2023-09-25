package com.ruoyi.client.config;

import com.ruoyi.client.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 16956
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Resource
//    private AuthInterceptor authInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor).addPathPatterns("/client/**")
//             .excludePathPatterns("/client/user/login","/client/user/register","/client/system/toLogin","/client/system/toRegister");
//    }
}
