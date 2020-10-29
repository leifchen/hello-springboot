package com.chen.controller;

import com.chen.bean.User;
import com.chen.util.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JwtController
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
@RestController
public class JwtController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            return JwtUtils.generate(user.getUsername());
        }
        return "账号密码错误";
    }
}
