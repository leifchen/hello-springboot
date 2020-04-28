package com.chen.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试控制器
 * <p>
 * @Author LeifChen
 * @Date 2020-04-27
 */
@Controller
public class TestController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/401")
    public String error() {
        return "401";
    }

    @RequestMapping("/delete")
    public String delete() {
        return "delete";
    }

    @RequestMapping("/select")
    public String select() {
        return "select";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/login_error")
    public String loginError(Model model) {
        model.addAttribute("login_error", "用户名或密码错误");
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("login_error", "注销成功");
        return "/login";
    }
}


