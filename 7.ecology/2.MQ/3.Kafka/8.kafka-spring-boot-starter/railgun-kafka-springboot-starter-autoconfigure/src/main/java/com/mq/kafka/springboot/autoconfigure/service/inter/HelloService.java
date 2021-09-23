package com.mq.kafka.springboot.autoconfigure.service.inter;

import com.mq.kafka.springboot.autoconfigure.properties.HelloProperties;

/**
 * @Author: railgun
 * 2021/9/17 1:04
 * PS: 接口
 */
public interface HelloService {

    /**
     * PS: 设置属性值
     *
     * @param helloProperties 自动注入的属性
     * @throws Exception: 2021/9/17 1:12
     * @Author: railgun
     * @return: void
     */
    void setHelloProperties(HelloProperties helloProperties);

    /**
     * PS: hello
     *
     * @param name
     * @throws Exception: 2021/9/17 1:12
     * @Author: railgun
     * @return: java.lang.String
     */
    String sayHello(String name);

}
