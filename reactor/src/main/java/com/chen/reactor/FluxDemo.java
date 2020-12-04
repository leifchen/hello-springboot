package com.chen.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Flux 示例
 * <p>
 * @Author LeifChen
 * @Date 2020-12-04
 */
public class FluxDemo {

    public static void main(String[] args) {
        print("start...");
        Flux.just("A", "B", "C")
                // 线程切换
                .publishOn(Schedulers.elastic())
                // 转换
                .map(value -> value + "+")
                .subscribe(
                        // 数据消费
                        FluxDemo::print,
                        // 异常处理
                        FluxDemo::print,
                        // 完成回调
                        () -> print("end!"),
                        // 背压操作
                        subscription -> {
                            for (int i = 0; i < 3; i++) {
                                subscription.request(1);
                                if (i == 1) {
                                    throw new RuntimeException("exception");
                                }
                            }
                        }
                )
        ;
    }

    private static void print(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[线程：" + threadName + "] " + object);
    }
}
