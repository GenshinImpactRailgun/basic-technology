package com.mq.kafka.springbootdemo.Dto;

import com.basic.comon.constant.KafkaConstant;
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
 * NoArgsConstructor：无参构造函数
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

    /**
     * railgun
     * 2021/9/13 9:09
     * PS: 初始化 kafka 消息传递载体对象
     */
    public static KafkaMessageDto initKafkaMessageDto(String id, String content) {
        KafkaMessageDto dto = new KafkaMessageDto();
        dto.setId(id);
        dto.setContent(content);
        dto.setTopic(KafkaConstant.TEST_TOPIC);
        return dto;
    }

}
