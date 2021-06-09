package com.frame.spring.proxy.demo1;

/**
 * @Author: railgun
 * 2021/6/9 19:43
 * PS: 客户端
 **/
public class Client {
    public static void main(String[] args) {
        // 准备租房的一个房东
        HostImpl hostImpl = new HostImpl();
        // 托管给中介进行代理
        IntermediaryProxyImpl proxy = new IntermediaryProxyImpl(hostImpl);
        // 客户端调用中介的出租方法
        proxy.rent();
        // 客户端付费用给中介
        proxy.getRentExpenses(0);
    }
}
