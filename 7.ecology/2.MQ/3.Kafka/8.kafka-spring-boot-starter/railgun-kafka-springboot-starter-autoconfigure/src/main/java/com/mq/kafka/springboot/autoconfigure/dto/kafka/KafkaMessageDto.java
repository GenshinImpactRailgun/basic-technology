package com.mq.kafka.springboot.autoconfigure.dto.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.Serializable;

/**
 * @Author: railgun
 * 2021/9/19 18:07
 * PS: kafka 消息交互的 dto
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class KafkaMessageDto implements Serializable {

    /**
     * 2021/9/19 18:29 @railgun 主题名称
     */
    private String topic;

    /**
     * 2021/9/19 18:29 @railgun partition 分区序号【从 0 开始】
     */
    private Integer partition;

    /**
     * 2021/9/19 18:29 @railgun key 发送消息的 key【当 partition 为空的时候，会使用该值确定发送消息到哪一个 partition】
     */
    private String key;

    /**
     * 2021/9/19 18:31 @railgun 发送消息的数据
     */
    private String data;

    /**
     * 2021/9/19 18:38 @railgun 数据对象
     */
    private Object dataObject;

    /**
     * 2021/9/19 18:33 @railgun 全类名
     */
    private String dataFullClassName;

    /**
     * 2021/9/19 18:50 @railgun 是否异步发送消息
     */
    private boolean async;

    /**
     * 2021/9/19 18:55 @railgun kafka 同步发送消息的结果
     */
    private SendResult<String, String> sendResult;

    /**
     * 2021/9/19 18:58 @railgun kafka 异步发送消息的结果
     */
    private ListenableFuture<SendResult<String, String>> listenableFuture;

}
