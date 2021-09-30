package com.java.designpattern.observer.contrast.interfaceto;

import com.java.designpattern.observer.service.inter.Observer;

/**
 * @Author: railgun
 * 2021/9/30
 * PS: 观察者【blibli 用户】
 */
public class ConsumerUser implements Observer {

    private VideoProducer videoProducer;

    public ConsumerUser(VideoProducer videoProducer) {
        videoProducer.registerObserver(this);
    }

    @Override
    public void onMessage() {
        System.out.println("接收到了消息");
    }

}
