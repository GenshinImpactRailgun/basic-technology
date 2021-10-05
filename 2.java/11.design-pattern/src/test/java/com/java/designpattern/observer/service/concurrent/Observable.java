package com.java.designpattern.observer.service.concurrent;

import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: railgun
 * 2021/10/5 14:40
 * PS: 被观察者
 */
@ToString
public class Observable {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 2021/10/5 14:55 @railgun 观察者集合
     */
    private List<Observer> observerList;

    public Observable() {
        observerList = new CopyOnWriteArrayList<>();
    }

    /**
     * railgun
     * 2021/10/5 16:04
     * PS: 添加观察者
     */
    public void registerObserver(Observer observer) {
        if (null == observer) {
            // 加入的对象为空则抛出空指针异常
            throw new NullPointerException();
        }
        observerList.add(observer);
    }

    /**
     * railgun
     * 2021/10/5 16:04
     * PS: 删除观察者
     */
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    /**
     * railgun
     * 2021/10/5 15:01
     * PS: 发送通知
     */
    public void notifyObservers() {
        if (CollectionUtils.isNotEmpty(observerList)) {
            Object[] arrLocal;
            synchronized (this) {
                // synchronized 拷贝对象
                arrLocal = observerList.toArray();
            }
            for (Object item : arrLocal) {
                ((Observer) item).onMessage(this);
            }
        }
    }

}
