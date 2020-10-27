package com.chen.controller;

import com.chen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
@RestController
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/api")
    public String api() {
        userService.doSomething();
        return "api成功返回数据";
    }
}
