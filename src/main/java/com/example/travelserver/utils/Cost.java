package com.example.travelserver.utils;

/**
 * 计算代码块耗时类
 * 优点是：简单，适用范围广泛，且适合统一管理；缺点是依然有代码侵入
 */
public class Cost implements AutoCloseable {
    private long start;

    public Cost() {
        this.start = System.currentTimeMillis();
    }

    @Override
    public void close() {
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }
}
