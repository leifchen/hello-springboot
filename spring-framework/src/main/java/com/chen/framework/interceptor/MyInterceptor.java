package com.chen.framework.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 * <p>
 * @Author LeifChen
 * @Date 2020-04-28
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("MyInterceptor - preHandle");
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("MyInterceptor - postHandle");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if ("LeifChen".equals(name)) {
            log.info("☆☆☆当前浏览器存在session");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("MyInterceptor - afterCompletion");
        long startTime = (Long) request.getAttribute("startTime");
        log.info("☆☆☆请求耗时：" + (System.currentTimeMillis() - startTime));
    }
}
