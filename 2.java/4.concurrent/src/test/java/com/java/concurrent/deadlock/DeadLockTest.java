package com.java.concurrent.deadlock;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: railgun
 * 2021/5/8 23:00
 * PS: 死锁
 **/
public class DeadLockTest {

    public static void main(String[] args) {
        deadlock();
    }

    /**
     * railgun
     * 2021/5/13 17:32
     * PS: 死锁示例
     **/
    public static void deadlock() {
        Object a = new Object(), b = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("railgun1");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println("railgun2");
                }
            }
        });
        thread1.start();
        thread2.start();
    }

}
