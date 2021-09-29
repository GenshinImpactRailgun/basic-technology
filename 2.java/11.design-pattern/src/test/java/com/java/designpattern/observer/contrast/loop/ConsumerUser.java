package com.java.designpattern.observer.contrast.loop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
     * 2021/9/29 @railgun 关注 up 主 列表
     */
    private List<VideoProducer> followList = new CopyOnWriteArrayList<>();

    /**
     * 2021/9/29 @railgun 维护的 up 主 主键以及现有视频数量【测试不考虑删除视频的情况】
     */
    private Map<String, Integer> producerVideoNumber = new ConcurrentHashMap<>();

    /**
     * railgun
     * 2021/9/29
     * PS: 关注某个 up 主
     */
    public void addVideoProducer(VideoProducer videoProducer) {
        this.followList.add(videoProducer);
    }

    /**
     * railgun
     * 2021/9/29
     * PS: 视频消费者接收到了消息
     */
    public void onMessage(VideoProducer videoProducer) {
        System.out.println(videoProducer.getNickname() + "，的视频更新啦" + " ====== 线程：" + Thread.currentThread().getName() + " ====== 视频总数：" + videoProducer.getVideoSize());
    }

}
