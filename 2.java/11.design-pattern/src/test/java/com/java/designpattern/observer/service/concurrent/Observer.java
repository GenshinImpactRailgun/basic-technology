package com.java.designpattern.observer.service.concurrent;

/**
 * @Author: railgun
 * 2021/10/5 14:58
 * PS: 观察者【仅有一个接收消息的方法】
 */
public interface Observer {

    /**
     * PS: 接收消息
     * 2021/10/5 14:59
     *
     * @param observable 被观察者的引用
     * @Author: railgun
     * @return: void
     */
    void onMessage(Observable observable);

}
