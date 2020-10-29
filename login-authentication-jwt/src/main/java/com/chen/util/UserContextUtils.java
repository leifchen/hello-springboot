package com.chen.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * RequestContext
 * <p>
 * @Author LeifChen
 * @Date 2020-10-27
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserContextUtils {

    private static final ThreadLocal<String> USER = new ThreadLocal<>();

    public static void add(String userName) {
        USER.set(userName);
    }

    public static void remove() {
        USER.remove();
    }

    /**
     * @return 当前登录用户的用户名
     */
    public static String getCurrentUserName() {
        return USER.get();
    }
}
