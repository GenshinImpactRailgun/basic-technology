package com.java.designpattern.observer.service.inter;

/**
 * @Author: railgun
 * 2021/9/27
 * PS: 主题【被订阅对象】
 */
public interface Subject {

    /**
     * PS: 注册观察者
     *
     * @param observer 观察者对象
     * @Author: railgun
     * @return: void
     * 2021/9/27
     */
    void registerObserver(Observer observer);

    /**
     * PS: 删除观察者
     *
     * @param observer 观察者对象
     * @Author: railgun
     * @return: void
     * 2021/9/27
     */
    void removeObserver(Observer observer);

    /**
     * PS: 通知观察者
     *
     * @Author: railgun
     * @return: void
     * 2021/9/27
     */
    void notifyObservers();

}
