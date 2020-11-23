package com.chen.rest.config;

import com.chen.rest.http.converter.PropertiesHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * RestWebMvcConfigurer
 * <p>
 * @Author LeifChen
 * @Date 2020-11-23
 */
@Configuration
public class RestWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加到集合首位
        converters.set(0, new PropertiesHttpMessageConverter());
    }
}
