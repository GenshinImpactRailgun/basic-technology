package com.java.designpattern.observer.contrast.loop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: railgun
 * 2021/9/29
 * PS: 视频 dto
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class VideoDto {

    /**
     * 2021/9/29 @railgun 视频标题
     */
    private String title;

    /**
     * 2021/9/29 @railgun 视频内容
     */
    private String content;

    /**
     * 2021/9/29 @railgun 上传时间
     */
    private Date uploadTime;

}
