package com.java.designpattern.observer.contrast.interfaceto;

import com.java.designpattern.observer.service.inter.Observer;

/**
 * @Author: railgun
 * 2021/9/30
 * PS: 观察者【blibli 用户】
 */
public class ConsumerUser implements Observer {

    /**
     * 2021/10/5 14:15 @railgun 主题对应的引用【接收通知之后，数据从该引用中获取】
     */
    private final VideoProducer videoProducer;

    public ConsumerUser(VideoProducer videoProducer) {
        this.videoProducer = videoProducer;
        videoProducer.registerObserver(this);
    }

    @Override
    public void onMessage() {
        System.out.println("接收到了消息为：" + videoProducer.toString());
    }

}
