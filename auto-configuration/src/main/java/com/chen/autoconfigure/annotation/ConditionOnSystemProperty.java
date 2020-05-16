package com.chen.autoconfigure.annotation;

import com.chen.autoconfigure.condition.OnSystemPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * Java 系统属性条件判断注解
 * <p>
 * @Author LeifChen
 * @Date 2020-05-16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionOnSystemProperty {

    /**
     * 属性名
     * @return
     */
    String name();

    /**
     * 属性值
     * @return
     */
    String value();
}
