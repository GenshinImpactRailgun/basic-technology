package com.java.designpattern.observer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author: railgun
 * 2021/9/27
 * PS: 通知消息内容
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class NoticeMessageDto {
    /**
     * 2021/9/27 @railgun 主题
     */
    private String topic;

    /**
     * 2021/9/27 @railgun 内容
     */
    private String content;
}
