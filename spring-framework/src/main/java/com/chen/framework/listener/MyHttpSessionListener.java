package com.chen.framework.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 自定义Session监听器
 * <p>
 * @Author LeifChen
 * @Date 2020-04-28
 */
@Slf4j
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("MyHttpSessionListener - sessionCreated");
        online++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("MyHttpSessionListener - sessionDestroyed");
        online--;
    }
}
