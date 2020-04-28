package com.chen.shiro.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Shiro控制器
 * <p>
 * @Author LeifChen
 * @Date 2020-04-27
 */
@Controller
public class ShiroController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/401")
    public String unauthorizedRole() {
        return "401";
    }

    @GetMapping("/delete")
    @RequiresRoles("admin")
    public String delete() {
        return "delete";
    }

    @GetMapping("/select")
    @RequiresPermissions("select")
    public String select() {
        return "select";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(HttpServletRequest request, Map<String, Object> map) {
        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "用户名不存在！";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "密码错误！";
            } else {
                msg = exception;
            }
        }
        map.put("msg", msg);
        return "/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/login";
    }
}


