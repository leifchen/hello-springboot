package com.chen.framework.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * 自定义过滤器
 * <p>
 * @Author LeifChen
 * @Date 2020-04-28
 */
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("MyFilter - init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilter - doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        // 只有符合条件的可以直接请求，不符合的跳转到/online请求
        String requestUrl = httpServletRequest.getRequestURI();
        log.info("请求地址:{}", requestUrl);
        if (requestUrl.indexOf("/addSession") != -1
                || requestUrl.indexOf("/removeSession") != -1
                || requestUrl.indexOf("/online") != -1
                || requestUrl.indexOf("/favicon.ico") != -1) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            wrapper.sendRedirect("/online");
        }
    }

    @Override
    public void destroy() {
        log.info("MyFilter - destroy");
    }
}
