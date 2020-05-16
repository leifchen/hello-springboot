package com.chen.autoconfigure.configuration;

import com.chen.autoconfigure.annotation.ConditionOnSystemProperty;
import com.chen.autoconfigure.annotation.EnableHelloWorld;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld自动装配
 * <p>
 * @Author LeifChen
 * @Date 2020-05-16
 */
@Configuration
@EnableHelloWorld
@ConditionOnSystemProperty(name = "user.name", value = "LeifChen")
public class HelloWorldAutoConfiguration {
}
