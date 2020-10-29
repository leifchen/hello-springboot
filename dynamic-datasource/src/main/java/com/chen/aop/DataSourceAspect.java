package com.chen.aop;

import com.chen.annotation.DataSource;
import com.chen.config.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态数据源AOP切面配置
 * <p>
 * @Author LeifChen
 * @Date 2020-05-06
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.chen.annotation.DataSource)")
    public void dataSourcePointCut() {
        // pointcut
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (dataSource == null) {
            DynamicDataSource.setDataSource("master");
        } else {
            DynamicDataSource.setDataSource(dataSource.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }
}
