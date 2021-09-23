package com.mq.kafka.springboot.autoconfigure.processor;

import com.mq.kafka.springboot.autoconfigure.properties.KafkaProperties;
import com.mq.kafka.springboot.autoconfigure.service.inter.message.consumer.KafkaConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

/**
 * @Author: railgun
 * 2021/9/22 0:52
 * PS: kafka 处理器
 */
@Slf4j
public class KafkaProcessor implements BeanPostProcessor {

    private final KafkaProperties kafkaProperties;

    public KafkaProcessor(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ConcurrentMessageListenerContainer) {
            ConcurrentMessageListenerContainer<String, Object> listenerContainer = ((ConcurrentMessageListenerContainer<String, Object>) bean);
            log.info("listenerContainer：{}", listenerContainer);
        }
        if (bean instanceof KafkaMessageListenerContainer) {
            KafkaMessageListenerContainer<String, Object> listenerContainer = ((KafkaMessageListenerContainer<String, Object>) bean);
            log.info("listenerContainer：{}", listenerContainer);
        }
        if (bean instanceof KafkaConsumerService) {
            KafkaConsumerService consumerService = ((KafkaConsumerService) bean);
            log.info("consumerService：{}", consumerService);
        }
        if (bean instanceof KafkaListener) {
            KafkaListener kafkaListener = ((KafkaListener) bean);
            log.info("kafkaListener：{}", kafkaListener);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
