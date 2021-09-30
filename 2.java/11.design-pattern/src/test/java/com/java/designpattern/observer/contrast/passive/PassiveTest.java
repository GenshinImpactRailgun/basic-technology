package com.java.designpattern.observer.contrast.passive;

import java.util.UUID;

/**
 * @Author: railgun
 * 2021/9/29
 * PS: passive 测试
 */
public class PassiveTest {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        VideoProducer videoProducer = new VideoProducer();
        videoProducer.setId(UUID.randomUUID().toString());
        videoProducer.setNickname("kiana kaslana");

        ConsumerUser consumerUser = new ConsumerUser();
        consumerUser.setNickname("railgun");

        ConsumerUser consumerUser1 = new ConsumerUser();
        consumerUser1.setNickname("railgun1");

        ConsumerUser consumerUser2 = new ConsumerUser();
        consumerUser2.setNickname("railgun2");

        videoProducer.registerObserver(consumerUser);
        videoProducer.registerObserver(consumerUser1);
        videoProducer.registerObserver(consumerUser2);

        videoProducer.removeObserver(consumerUser1);

        for (int i = 0; i < 10; i++) {
            videoProducer.uploadVideo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
