package com.chen;

import com.chen.interceptor.LoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * App
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
@SpringBootApplication
public class App implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 使拦截器生效
        registry.addInterceptor(new LoginInterceptor());
    }
}
