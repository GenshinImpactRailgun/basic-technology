package com.java.designpattern.observer.service.impl;

import com.java.designpattern.observer.dto.NoticeMessageDto;
import com.java.designpattern.observer.service.inter.Observer;
import com.java.designpattern.observer.service.inter.Subject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: railgun
 * 2021/9/27
 * PS: 主题【老师通知学生这个链路中的老师对象】
 */
public class Teacher implements Subject {

    /**
     * 2021/9/27 @railgun 发送通知的消息体
     */
    private NoticeMessageDto noticeMessageDto;

    public NoticeMessageDto getNoticeMessageDto() {
        return noticeMessageDto;
    }

    public void setNoticeMessageDto(NoticeMessageDto noticeMessageDto) {
        this.noticeMessageDto = noticeMessageDto;
    }

    /**
     * 2021/9/27 @railgun 订阅的集合对象
     */
    private static final List<Observer> SUBSCRIBE_LIST = new CopyOnWriteArrayList<>();

    /**
     * railgun
     * 2021/9/27
     * PS: 注册观察者
     */
    @Override
    public void registerObserver(Observer observer) {
        SUBSCRIBE_LIST.add(observer);
    }

    /**
     * railgun
     * 2021/9/27
     * PS: 删除观察者
     */
    @Override
    public void removeObserver(Observer observer) {
        SUBSCRIBE_LIST.remove(observer);
    }

    /**
     * railgun
     * 2021/9/27
     * PS: 发送通知给所有观察者
     */
    @Override
    public void notifyObservers() {
        // 遍历所有订阅的对象，发送通知
        SUBSCRIBE_LIST.forEach(Observer::onMessage);
    }

}
