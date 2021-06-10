package com.frame.spring.aop.service;

/**
 * @Author: railgun
 * 2021/6/10 0:59
 * PS: 自定义方法
 **/
public class DiyServiceImpl {
    public void before() {
        System.out.println("方法执行前调用");
    }

    public void after() {
        System.out.println("方法执行后调用");
    }
}
