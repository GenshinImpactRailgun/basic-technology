package com.java.designpattern.observer.service.concurrent;

import org.junit.jupiter.api.Test;

/**
 * @Author: railgun
 * 2021/10/5 16:14
 * PS: 并发
 */
public class ConcurrentTest {

    @Test
    public void test1() {

        VideoProducer videoProducer = new VideoProducer();

        ConsumerUser consumerUser1 = new ConsumerUser();
        ConsumerUser consumerUser2 = new ConsumerUser();
        ConsumerUser consumerUser3 = new ConsumerUser();

        videoProducer.registerObserver(consumerUser1);
        videoProducer.registerObserver(consumerUser2);
        videoProducer.registerObserver(consumerUser3);

        videoProducer.setContent("这是要执行通知的一段内容");

        videoProducer.notifyObservers();

    }

}
