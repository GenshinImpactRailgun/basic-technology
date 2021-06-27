package com.frame.springboot.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: railgun
 * 2021/6/27 21:46
 * PS:
 **/
@Service
public class AsyncServiceImpl implements AsyncService {

    /**
     * railgun
     * 2021/6/27 21:49
     * PS: 异步执行该任务
     **/
    @Async
    @Override
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务执行结束....");
    }

}
