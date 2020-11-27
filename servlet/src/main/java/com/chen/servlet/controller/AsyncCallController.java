package com.chen.servlet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * AsyncCallController
 * <p>
 * @Author LeifChen
 * @Date 2020-11-27
 */
@Slf4j
@RestController
public class AsyncCallController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "/call")
    public Map<String, Object> asyncCall() {
        Map<String, Object> resMap = new HashMap<>(4);
        try {
            // 通过上下文获取自己的代理对象调用异步方法
            AsyncCallController asyncCallController = applicationContext.getBean(AsyncCallController.class);
            asyncCallController.testAsyncTask();
            resMap.put("code", 200);
        } catch (Exception e) {
            resMap.put("code", 400);
            log.error("error!", e);
        }
        return resMap;
    }

    @Async
    public void testAsyncTask() throws InterruptedException {
        Thread.sleep(3000);
        log.info("异步任务执行完成！");
    }
}
