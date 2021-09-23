package com.mq.kafka.springboot.autoconfigure.exception;

/**
 * @Author: railgun
 * 2021/9/19 19:47
 * PS: kafka 相关异常
 */
public class KafkaCustomException extends RuntimeException {

    /**
     * railgun
     * 2021/9/19 19:49
     * PS: 自定义异常结构
     */
    public KafkaCustomException(String errorMessage) {
        super(errorMessage);
    }

}
