package com.manager.conf;

import com.manager.manager.intercepter.AccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @Configuration
 * 标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
 * @author zym
 */
@Configuration
public class InterceperConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对来自/user/** 这个链接来的请求进行拦截
        registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/errBkServer/jsonData/**");
        super.addInterceptors(registry);

    }
}