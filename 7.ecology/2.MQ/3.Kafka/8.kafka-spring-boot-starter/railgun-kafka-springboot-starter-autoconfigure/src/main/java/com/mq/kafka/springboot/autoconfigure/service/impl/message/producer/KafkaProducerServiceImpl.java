package com.mq.kafka.springboot.autoconfigure.service.impl.message.producer;

import com.basic.comon.util.GsonUtil;
import com.mq.kafka.springboot.autoconfigure.dto.kafka.KafkaMessageDto;
import com.mq.kafka.springboot.autoconfigure.exception.KafkaCustomException;
import com.mq.kafka.springboot.autoconfigure.service.inter.message.producer.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @Author: railgun
 * 2021/9/19 17:51
 * PS: kafka 生产者接口实现类
 */
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

    /**
     * railgun
     * 2021/9/19 17:48
     * PS: 引入 kafkaTemplate
     */
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * railgun
     * 2021/9/11 11:29
     * PS: 同步发送消息
     */
    @Override
    public void sendMessage(KafkaMessageDto kafkaMessageDto) throws KafkaCustomException {
        // TODO 执行初始化逻辑
        if (null == kafkaMessageDto) {
            log.error("同步发送消息的参数为 null，非法");
            throw new KafkaCustomException("抛出异常给方法调用方");
        }
        if (StringUtils.isBlank(kafkaMessageDto.getData())) {
            kafkaMessageDto.setData(GsonUtil.objectToJson(kafkaMessageDto.getDataObject()));
        }
        try {
            if (kafkaMessageDto.isAsync()) {
                // 异步发送消息
                kafkaMessageDto.setListenableFuture(kafkaTemplate.send(kafkaMessageDto.getTopic(), kafkaMessageDto.getPartition(), kafkaMessageDto.getKey(), kafkaMessageDto.getData()));
            } else {
                // 同步发送消息
                kafkaMessageDto.setSendResult(kafkaTemplate.send(kafkaMessageDto.getTopic(), kafkaMessageDto.getPartition(), kafkaMessageDto.getKey(), kafkaMessageDto.getData()).get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * railgun
     * 2021/9/19 19:10
     * PS: 事务模式发送消息
     */
    @Override
    public void sendMessageTransactional(KafkaMessageDto kafkaMessageDto, Runnable runner) {
        kafkaTemplate.executeInTransaction(kafkaOperations -> {
            // TODO 执行事务发送消息之前
            try {
                kafkaMessageDto.setSendResult(kafkaOperations.send(kafkaMessageDto.getTopic(), kafkaMessageDto.getPartition(), kafkaMessageDto.getKey(), kafkaMessageDto.getData()).get());
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
