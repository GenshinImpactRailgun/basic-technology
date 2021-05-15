package com.java.jvm;

import lombok.SneakyThrows;

public class Test {

    private static class GcListenDemo {
        @Override
        public void finalize() {
            System.out.println("GcListenDemo finalize 被回收，回收方法执行");
        }
    }

    /**
     * railgun
     * 2021/5/15 16:12
     * PS: 垃圾回收触发方法
     * 1、主程序需阻塞，否则 GC 线程会因为主线程退出而结束不再执行垃圾回收操作
     * 2、禁止重写 finalize 方法，会导致整个 GC 时间延长，而导致更加频繁的 FGC 更严重的造成 OOM
     **/
    @SneakyThrows
    @org.junit.jupiter.api.Test
    public void test1() {
        GcListenDemo gcListenDemo = new GcListenDemo();
        gcListenDemo = null;
        System.gc();

        // 加阻塞。因为 GC 是守护线程，主线程结束之后，GC 线程也就结束了，不一定来得及进行垃圾回收
        System.in.read();
    }

}
