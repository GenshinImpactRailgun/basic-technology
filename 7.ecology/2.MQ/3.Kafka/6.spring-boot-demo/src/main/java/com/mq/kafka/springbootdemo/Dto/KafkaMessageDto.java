package com.mq.kafka.springbootdemo.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: railgun
 * 2021/9/11 11:18
 * PS:
 * Accessors：支持链式编程
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class KafkaMessageDto implements Serializable {

    /**
     * 2021/9/11 11:23 @railgun topic
     */
    private String topic;

    /**
     * 2021/9/11 11:19 @railgun uuid
     */
    private String id;

    /**
     * 2021/9/11 11:19 @railgun name
     */
    private String name;

    /**
     * 2021/9/11 11:19 @railgun content
     */
    private String content;

    /**
     * 2021/9/11 11:18 @railgun time
     */
    private String timeStamp;

}
