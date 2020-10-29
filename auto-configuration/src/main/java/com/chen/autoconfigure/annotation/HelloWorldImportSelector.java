package com.chen.autoconfigure.annotation;

import com.chen.autoconfigure.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * HelloWorld实现
 * <p>
 * @Author LeifChen
 * @Date 2020-05-16
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
