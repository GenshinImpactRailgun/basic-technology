package com.mq.kafka.springboot.autoconfigure.service.impl;

import com.mq.kafka.springboot.autoconfigure.properties.HelloProperties;
import com.mq.kafka.springboot.autoconfigure.service.inter.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author: railgun
 * 2021/9/17 1:06
 * PS:
 */
@Service
public class HelloServiceImpl implements HelloService {

    HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    @Override
    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    @Override
    public String sayHello(String name) {
        return helloProperties.getPrefix() + "-" + name + helloProperties.getSuffix();
    }

}
