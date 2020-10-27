package com.chen;

import com.chen.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 程序主入口
 * <p>
 * @Author LeifChen
 * @Date 2020-05-06
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.chen.mapper")
@Import({DynamicDataSourceConfig.class})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
