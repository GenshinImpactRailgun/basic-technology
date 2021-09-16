package com.mq.kafka.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.*;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

/**
 * @Author: railgun
 * 2021/9/12 16:55
 * PS: kafka 配置，处理死信队列
 */
@Configuration
public class KafkaConfiguration {

    /**
     * railgun
     * 2021/9/13 0:32
     * PS: 单条消费异常的重试处理
     */
    @Bean
    @Primary
    public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {
        // <1> 创建 DeadLetterPublishingRecoverer 对象
        ConsumerRecordRecoverer recoverer = new DeadLetterPublishingRecoverer(template);
        // <2> 创建 FixedBackOff 对象
        BackOff backOff = new FixedBackOff(4 * 1000L, 3L);
        // <3> 创建 SeekToCurrentErrorHandler 对象
        return new SeekToCurrentErrorHandler(recoverer, backOff);
    }

    /**
     * railgun
     * 2021/9/13 0:32
     * PS: 批量消费异常的重试处理
     */
    @Bean
    @Primary
    public BatchErrorHandler kafkaBatchErrorHandler() {
        // 创建 SeekToCurrentBatchErrorHandler 对象
        SeekToCurrentBatchErrorHandler batchErrorHandler = new SeekToCurrentBatchErrorHandler();
        // 创建 FixedBackOff 对象 TODO 时间间隔不对，重试次数不对，不止 3 次
        BackOff backOff = new FixedBackOff(3 * 1000L, 3L);
        batchErrorHandler.setBackOff(backOff);
        // 返回
        return batchErrorHandler;
    }

}
