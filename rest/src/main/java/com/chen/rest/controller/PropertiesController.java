package com.chen.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * PropertiesController
 * <p>
 * @Author LeifChen
 * @Date 2020-11-23
 */
@RestController
public class PropertiesController {

    @PostMapping(value = "/add/props", consumes = "text/properties;charset=UTF-8")
    public Properties addProperties(@RequestBody Properties properties) {
        return properties;
    }
}
