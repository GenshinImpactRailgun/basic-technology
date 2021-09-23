package com.mq.kafka.springboot.autoconfigure.config;

import com.mq.kafka.springboot.autoconfigure.processor.KafkaProcessor;
import com.mq.kafka.springboot.autoconfigure.properties.KafkaProperties;
import com.mq.kafka.springboot.autoconfigure.service.impl.message.producer.KafkaProducerServiceImpl;
import com.mq.kafka.springboot.autoconfigure.service.impl.message.producer.KafkaProducerServiceTwoImpl;
import com.mq.kafka.springboot.autoconfigure.service.inter.message.consumer.KafkaConsumerService;
import com.mq.kafka.springboot.autoconfigure.service.inter.message.producer.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @Author: railgun
 * 2021/9/17 1:08
 * PS:
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaAutoConfiguration {

    /**
     * railgun
     * 2021/9/19 22:50
     * PS: 当 spring 容器中没有 producerService bean 的时候，注入该 bean，使用其他方式发送消息
     */
    @Bean
    @ConditionalOnMissingBean(name = "producerService")
    public KafkaProducerService producerService() {
        return new KafkaProducerServiceTwoImpl();
    }

    /**
     * railgun
     * 2021/9/19 22:49
     * PS: kafka 生产者配置类
     * Configuration            声明这是配置类，里面的内容会被放到 spring 容器中
     * ConditionalOnClass       当有这个 class 的时候生效
     * ConditionalOnProperty    当有该配置的时候生效
     */
    @Configuration
    @ConditionalOnClass(name = {"org.springframework.kafka.core.KafkaTemplate"})
    @ConditionalOnProperty(prefix = "mq", name = "type", havingValue = "kafka", matchIfMissing = true)
    @Import(KafkaAutoConfiguration.class)
    protected static class KafkaProducerClass {

        /**
         * railgun
         * 2021/9/20 20:58
         * PS: 优先执行注入该 bean【使用 kafka 发送消息】
         */
        @Bean
        public KafkaProducerService producerService() {
            return new KafkaProducerServiceImpl();
        }
    }

    /**
     * railgun
     * 2021/9/20 21:22
     * PS: kafka 消费者配置类
     * railgun.kafka.topic 如果没有该配置 KafkaConsumerClass 中配置的内容都会不生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "railgun.kafka", name = "topic", matchIfMissing = false)
    @Import(KafkaAutoConfiguration.class)
    protected static class KafkaConsumerClass {

        private final KafkaConsumerService consumerService;

        public KafkaConsumerClass(KafkaConsumerService consumerService) {
            this.consumerService = consumerService;
        }

        /**
         * railgun
         * 2021/9/20 21:18
         * PS: kafka 消费者消费消息
         */
        @KafkaListener(topics = "my-topic", groupId = "bronya-group")
        public void onMessage(ConsumerRecord<String, String> consumerRecord) {
            // 消费消息
            consumerService.consume(consumerRecord);
        }

        @Bean
        public KafkaProcessor kafkaProcessor(KafkaProperties kafkaProperties) {
            return new KafkaProcessor(kafkaProperties);
        }

    }

}
