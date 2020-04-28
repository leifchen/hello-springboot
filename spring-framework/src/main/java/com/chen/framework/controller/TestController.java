package com.chen.framework.controller;

import com.chen.framework.listener.MyHttpSessionListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 测试控制器
 * <p>
 * @Author LeifChen
 * @Date 2020-04-28
 */
@RestController
public class TestController {

    @GetMapping("/addSession")
    public String addSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", "LeifChen");
        return "当前在线人数:" + MyHttpSessionListener.online + "人";
    }

    @GetMapping("/removeSession")
    public String removeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "当前在线人数:" + MyHttpSessionListener.online + "人";
    }

    @GetMapping("/online")
    public String online() {
        return "当前在线人数:" + MyHttpSessionListener.online + "人";
    }
}
