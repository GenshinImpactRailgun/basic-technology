package com.mq.kafka.springboot.autoconfigure.service.inter.message.consumer;

import com.mq.kafka.springboot.autoconfigure.dto.kafka.KafkaMessageDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @Author: railgun
 * 2021/9/19 17:53
 * PS: kafka 消费者接口
 */
public interface KafkaConsumerService {

    /**
     * PS: 消费消息
     * 2021/9/20 15:30
     *
     * @param consumerRecord 消息主体内容
     * @Author: railgun
     * @return: void
     */
    void consume(ConsumerRecord<String, String> consumerRecord);

}
