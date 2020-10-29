package com.chen.autoconfigure.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 激活 HelloWorld 模块注解
 * <p>
 * @Author LeifChen
 * @Date 2020-05-16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
}
