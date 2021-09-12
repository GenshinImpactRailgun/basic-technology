package com.mq.kafka.springbootdemo.producer;

import com.basic.comon.constant.KafkaConstant;
import com.mq.kafka.springbootdemo.Dto.KafkaMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @Author: railgun
 * 2021/9/11 11:24
 * PS:
 */
@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * railgun
     * 2021/9/11 11:29
     * PS: 同步发送消息
     */
    public SendResult<String, Object> syncSend(String id, String content) {
        KafkaMessageDto message = new KafkaMessageDto();
        message.setTopic(KafkaConstant.TEST_TOPIC);
        message.setId(id);
        message.setContent(content);
        SendResult<String, Object> result = null;
        // 同步发送消息
        try {
            result = kafkaTemplate.send(message.getTopic(), message).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * railgun
     * 2021/9/11 11:29
     * PS: 异步发送消息
     */
    public ListenableFuture<SendResult<String, Object>> asyncSend(String id, String content) {
        KafkaMessageDto message = new KafkaMessageDto();
        message.setTopic(KafkaConstant.TEST_TOPIC);
        message.setId(id);
        message.setContent(content);
        // 异步发送消息
        ListenableFuture<SendResult<String, Object>> result = kafkaTemplate.send(message.getTopic(), message);
        // kafkaTemplate flush 调用之后可以立即发送 消息 给 broker，但是此次会话之后的消息就都不能发送了
        return result;
    }

}