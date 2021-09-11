package com.mq.kafka.springbootdemo.service.inter;

/**
 * @Author: railgun
 * 2021/9/11 11:04
 * PS:
 */
public interface DemoService {

    /**
     * PS: send message
     *
     * @param message message
     * @throws Exception: 2021/9/11 11:08
     * @Author: railgun
     * @return: void
     */
    void producerExecute(String message);

}
