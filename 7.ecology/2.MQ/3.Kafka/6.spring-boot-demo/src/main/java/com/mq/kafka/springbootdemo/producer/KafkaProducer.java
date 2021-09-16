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

    /**
     * railgun
     * 2021/9/13 9:08
     * PS: 发送消息到指定分区【依据 key 的 hash 值】
     */
    public ListenableFuture<SendResult<String, Object>> sendMessageToAppointPartitionByKey(String id, String content) {
        KafkaMessageDto message = KafkaMessageDto.initKafkaMessageDto(id, content);
        // 依据 key 的 hash 值，来确定投递到哪一个分区
        return kafkaTemplate.send(message.getTopic(), KafkaConstant.APPOINT_SEND_PARTITION_KEY, message);
    }

    /**
     * railgun
     * 2021/9/13 9:28
     * PS: 发送消息到指定分区【依据 partition【整形】 来指定】
     */
    public ListenableFuture<SendResult<String, Object>> sendMessageToAppointPartitionByPartition(String id, String content) {
        KafkaMessageDto message = KafkaMessageDto.initKafkaMessageDto(id, content);
        // 投递消息到第 partition【n，从 0 开始】 个 partition 分区
        return kafkaTemplate.send(message.getTopic(), 2, KafkaConstant.APPOINT_SEND_PARTITION_KEY, message);
    }

    /**
     * railgun
     * 2021/9/13 12:30
     * PS: 事务模式发送消息
     */
    public String syncSendInTransaction(Integer id, String content, Runnable runner) throws ExecutionException, InterruptedException {
        return kafkaTemplate.executeInTransaction(kafkaOperations -> {
            // 创建 Demo07Message 消息
            KafkaMessageDto message = KafkaMessageDto.initKafkaMessageDto(String.valueOf(id), content);
            try {
                SendResult<String, Object> sendResult = kafkaOperations.send(KafkaConstant.TEST_TOPIC, message).get();
                log.info("[doInOperations][发送编号：[{}] 发送结果：[{}]]", id, sendResult);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // 本地业务逻辑... biubiubiu
            runner.run();

            // 返回结果
            return "success";
        });
    }

}
