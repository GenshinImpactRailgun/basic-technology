package com.java.designpattern.observer.service.concurrent;

/**
 * @Author: railgun
 * 2021/10/5 16:10
 * PS: 观察者
 */
public class ConsumerUser implements Observer {
    @Override
    public void onMessage(Observable observable) {
        // 实现具体的业务场景
        System.out.println("接收到了消息：" + observable.toString());
    }
}
