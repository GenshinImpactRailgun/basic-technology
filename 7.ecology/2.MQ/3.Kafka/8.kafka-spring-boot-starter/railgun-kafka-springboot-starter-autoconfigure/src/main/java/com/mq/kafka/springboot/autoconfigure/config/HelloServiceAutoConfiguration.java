package com.mq.kafka.springboot.autoconfigure.config;

import com.mq.kafka.springboot.autoconfigure.properties.HelloProperties;
import com.mq.kafka.springboot.autoconfigure.service.impl.HelloServiceImpl;
import com.mq.kafka.springboot.autoconfigure.service.inter.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: railgun
 * 2021/9/17 1:08
 * PS:
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    private final HelloProperties helloProperties;

    public HelloServiceAutoConfiguration(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    @Bean(name = "railgunHelloService")
    public HelloService helloService() {
        HelloService helloService = new HelloServiceImpl();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }

}
