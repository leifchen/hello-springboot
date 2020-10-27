package com.chen.controller;

import com.chen.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * SessionController
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
@RestController
public class SessionController {

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            session.setAttribute("user", user);
            return "登录成功";
        }
        return "账号或密码错误";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "退出成功";
    }
}
