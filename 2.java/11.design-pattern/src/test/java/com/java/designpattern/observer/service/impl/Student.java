package com.java.designpattern.observer.service.impl;

import com.java.designpattern.observer.service.inter.Observer;
import com.java.designpattern.observer.service.inter.Subject;

/**
 * @Author: railgun
 * 2021/9/27
 * PS: 观察者【】
 */
public class Student implements Observer {

    public Subject subject;

    public Student(Subject subject) {
        // 对象赋值
        this.subject = subject;
        // 注册观察者
        subject.registerObserver(this);
    }

    @Override
    public void onMessage() {
        System.out.println(System.identityHashCode(this) + "：学生接收到了老师的通知");
    }

}
