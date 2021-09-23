package com.mq.kafka.springboot.autoconfigure.service.inter.message.producer;

import com.mq.kafka.springboot.autoconfigure.dto.kafka.KafkaMessageDto;
import com.mq.kafka.springboot.autoconfigure.exception.KafkaCustomException;

/**
 * @Author: railgun
 * 2021/9/19 17:50
 * PS: kafka 生产者接口
 */
public interface KafkaProducerService {

    /**
     * PS: 发送消息
     * 2021/9/19 20:02
     *
     * @param kafkaMessageDto 消息内容
     * @throws KafkaCustomException: 抛出的异常
     * @Author: railgun
     * @return: void
     */
    void sendMessage(KafkaMessageDto kafkaMessageDto) throws KafkaCustomException;

    /**
     * PS: 事务模式发送消息
     * 2021/9/19 19:56
     *
     * @param kafkaMessageDto 消息内容
     * @param runner          线程执行发昂阀体
     * @Author: railgun
     * @return: void
     */
    void sendMessageTransactional(KafkaMessageDto kafkaMessageDto, Runnable runner);

}
