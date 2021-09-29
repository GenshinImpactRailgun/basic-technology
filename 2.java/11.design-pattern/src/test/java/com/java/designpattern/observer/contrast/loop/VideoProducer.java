package com.java.designpattern.observer.contrast.loop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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
    }

    /**
     * railgun
     * 2021/9/29
     * PS: 获取 up 主 上传的视频数量
     */
    public int getVideoSize() {
        return videoDtoList.size();
    }

}
