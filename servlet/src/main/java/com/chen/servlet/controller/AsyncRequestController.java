package com.chen.servlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * AsyncRequestController
 * <p>
 * @Author LeifChen
 * @Date 2020-11-27
 */
@RestController
public class AsyncRequestController {

    @Autowired
    private ThreadPoolTaskExecutor myThreadPoolTaskExecutor;

    @GetMapping(value = "/servletReq")
    public void servletReq(HttpServletRequest request) {
        AsyncContext asyncContext = request.startAsync();
        // 设置监听器:可设置其开始、完成、异常、超时等事件的回调处理
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onTimeout(AsyncEvent event) {
                System.out.println("超时了...");
            }

            @Override
            public void onStartAsync(AsyncEvent event) {
                System.out.println("线程开始");
            }

            @Override
            public void onError(AsyncEvent event) {
                System.out.println("发生错误：" + event.getThrowable());
            }

            @Override
            public void onComplete(AsyncEvent event) {
                System.out.println("执行完成");
            }
        });
        asyncContext.setTimeout(20000);
        asyncContext.start(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("内部线程：" + Thread.currentThread().getName());
                asyncContext.getResponse().setCharacterEncoding("UTF-8");
                asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                asyncContext.getResponse().getWriter().println("这是异步的请求返回");
            } catch (Exception e) {
                System.out.println("异常：" + e);
            }
            // 异步请求完成通知
            asyncContext.complete();
        });
        System.out.println("主线程：" + Thread.currentThread().getName());
    }

    @GetMapping(value = "/callableReq")
    public Callable<String> callableReq() {
        System.out.println("外部线程：" + Thread.currentThread().getName());

        return () -> {
            Thread.sleep(1000);
            System.out.println("内部线程：" + Thread.currentThread().getName());
            return "callable!";
        };
    }

    @GetMapping(value = "/webAsyncReq")
    public WebAsyncTask<String> webAsyncReq() {
        System.out.println("外部线程：" + Thread.currentThread().getName());
        Callable<String> result = () -> {
            System.out.println("内部线程开始：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (Exception e) {
                // handle exception
            }
            System.out.println("副线程返回");
            System.out.println("内部线程返回：" + Thread.currentThread().getName());
            return "success";
        };
        WebAsyncTask<String> wat = new WebAsyncTask<>(3000L, result);
        wat.onTimeout(() -> "超时");
        return wat;
    }

    @GetMapping(value = "/deferredResultReq")
    public DeferredResult<String> deferredResultReq() {
        System.out.println("外部线程：" + Thread.currentThread().getName());
        DeferredResult<String> result = new DeferredResult<>(60 * 1000L);
        // 处理超时事件 采用委托机制
        result.onTimeout(() -> {
            System.out.println("DeferredResult超时");
            result.setResult("超时了!");
        });
        result.onCompletion(() -> System.out.println("调用完成"));
        myThreadPoolTaskExecutor.execute(() -> {
            System.out.println("内部线程：" + Thread.currentThread().getName());
            result.setResult("DeferredResult!!");
        });
        return result;
    }
}
