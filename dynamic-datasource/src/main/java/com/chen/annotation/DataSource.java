package com.chen.annotation;

import java.lang.annotation.*;

/**
 * 自定义数据源选择注解
 * <p>
 * @Author LeifChen
 * @Date 2020-05-06
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String name() default "";
}
