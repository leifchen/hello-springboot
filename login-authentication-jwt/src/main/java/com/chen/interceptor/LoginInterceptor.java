package com.chen.interceptor;

import com.chen.util.JwtUtils;
import com.chen.util.UserContextUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * LoginInterceptor
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 简单的白名单，登录这个接口直接放行
        if ("/login".equals(request.getRequestURI())) {
            return true;
        }

        // 从请求头中获取token字符串并解析
        Claims claims = JwtUtils.parse(request.getHeader("Authorization"));
        // 已登录就直接放行
        if (claims != null) {
            // 将我们之前放到token中的userName给存到上下文对象中
            UserContextUtils.add(claims.getSubject());
            return true;
        }

        // 走到这里就代表是其他接口，且没有登录
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("请先登录");
        out.flush();
        out.close();
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束后要从上下文对象删除数据，如果不删除则可能会导致内存泄露
        UserContextUtils.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
