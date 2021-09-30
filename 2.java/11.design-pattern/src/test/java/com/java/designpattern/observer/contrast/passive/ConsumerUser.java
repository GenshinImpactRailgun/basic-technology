package com.java.designpattern.observer.contrast.passive;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: railgun
 * 2021/9/29
 * PS: 视频消费者
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class ConsumerUser {

    /**
     * 2021/9/29 @railgun 昵称
     */
    private String nickname;

    /**
     * railgun
     * 2021/9/29
     * PS: 视频消费者接收到了消息
     */
    public void onMessage(VideoProducer videoProducer) {
        System.out.println(videoProducer.getNickname() + "，的视频更新啦" + " ====== 线程：" + Thread.currentThread().getName());
    }

}
