package com.chen.util;

import com.chen.bean.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * RequestContext
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
public class RequestContextUtils {

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    public static User getCurrentUser() {
        return (User) getCurrentRequest().getSession().getAttribute("user");
    }
}
