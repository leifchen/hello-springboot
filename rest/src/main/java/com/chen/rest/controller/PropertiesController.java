package com.chen.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Properties;

/**
 * PropertiesController
 * <p>
 * @Author LeifChen
 * @Date 2020-11-23
 */
@Controller
public class PropertiesController {

    @PostMapping(value = "/add/props", consumes = "text/properties;charset=UTF-8")
    public Properties addProperties(Properties properties) {
        return properties;
    }
}
