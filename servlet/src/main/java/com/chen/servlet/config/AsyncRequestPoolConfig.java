package com.chen.servlet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * AsyncRequestPoolConfig
 * <p>
 * @Author LeifChen
 * @Date 2020-11-27
 */
@Configuration
public class AsyncRequestPoolConfig implements WebMvcConfigurer {

    @Autowired
    private ThreadPoolTaskExecutor myThreadPoolTaskExecutor;

    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        // 处理 callable 超时
        configurer.setDefaultTimeout(60 * 1000L);
        configurer.setTaskExecutor(myThreadPoolTaskExecutor);
        configurer.registerCallableInterceptors(timeoutCallableProcessingInterceptor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutCallableProcessingInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }
}