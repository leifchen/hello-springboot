package com.chen.autoconfigure.configuration;

import org.springframework.context.annotation.Bean;

/**
 * HelloWorld配置
 * <p>
 * @Author LeifChen
 * @Date 2020-05-16
 */
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() {
        return "Hello World";
    }
}
