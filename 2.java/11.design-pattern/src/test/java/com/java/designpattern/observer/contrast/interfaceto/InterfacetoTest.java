package com.java.designpattern.observer.contrast.interfaceto;

/**
 * @Author: railgun
 * 2021/9/30
 * PS: 接口抽象测试
 */
public class InterfacetoTest {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        VideoProducer videoProducer = new VideoProducer();
        ConsumerUser consumerUser = new ConsumerUser(videoProducer);
        ConsumerUser consumerUser1 = new ConsumerUser(videoProducer);
        ConsumerUser consumerUser2 = new ConsumerUser(videoProducer);

        for (int i = 0; i < 5; i++) {
            videoProducer.notifyObservers();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
