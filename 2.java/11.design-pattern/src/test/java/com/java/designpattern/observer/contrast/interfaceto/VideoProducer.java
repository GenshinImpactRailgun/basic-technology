package com.java.designpattern.observer.contrast.interfaceto;

import com.java.designpattern.observer.service.inter.Observer;
import com.java.designpattern.observer.service.inter.Subject;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: railgun
 * 2021/9/30
 * PS: 主题【up 主】
 */
public class VideoProducer implements Subject {

    private final List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        if (CollectionUtils.isNotEmpty(observerList)) {
            observerList.forEach(Observer::onMessage);
        }
    }

}
