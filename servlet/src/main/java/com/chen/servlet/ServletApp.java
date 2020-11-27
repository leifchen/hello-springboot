package com.chen.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * ServletApp
 * <p>
 * @Author LeifChen
 * @Date 2020-11-27
 */
@EnableAsync
@SpringBootApplication
public class ServletApp {

    public static void main(String[] args) {
        SpringApplication.run(ServletApp.class, args);
    }
}
