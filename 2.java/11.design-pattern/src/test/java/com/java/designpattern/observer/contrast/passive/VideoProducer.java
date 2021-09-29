package com.java.designpattern.observer.contrast.passive;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: railgun
 * 2021/9/29
 * PS: up 主 对象
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class VideoProducer {

    /**
     * railgun
     * 2021/9/29
     * PS: 添加观察者
     */
    public void registerObserver(ConsumerUser consumerUser) {
        this.consumerUserList.add(consumerUser);
    }

    /**
     * railgun
     * 2021/9/29
     * PS: 移除观察者
     */
    public void removeObserver(ConsumerUser consumerUser) {
        this.consumerUserList.remove(consumerUser);
    }

    /**
     * railgun
     * 2021/9/29
     * PS: 通知消息给观察者
     */
    public void notifyObservers() {
        if (CollectionUtils.isNotEmpty(consumerUserList)) {
            consumerUserList.forEach(item -> item.onMessage(this));
        }
    }

    /**
     * 2021/9/29 @railgun 主键
     */
    private String id;

    /**
     * 2021/9/29 @railgun 昵称
     */
    private String nickname;

    /**
     * 2021/9/29 @railgun up 主 所有的视频
     */
    private List<VideoDto> videoDtoList = new CopyOnWriteArrayList<>();

    /**
     * 2021/9/29 @railgun 关注用户 list
     */
    private List<ConsumerUser> consumerUserList = new CopyOnWriteArrayList<>();

    /**
     * railgun
     * 2021/9/29
     * PS: 上传视频
     */
    public void uploadVideo() {
        VideoDto videoDto = new VideoDto();
        videoDto.setTitle("这是一个新视频（" + System.currentTimeMillis() + "）");
        videoDto.setContent("Bronya");
        videoDto.setUploadTime(new Date());
        videoDtoList.add(videoDto);
        notifyObservers();
    }

}
