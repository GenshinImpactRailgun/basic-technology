package com.frame.spring.proxy.demo1;

/**
 * @Author: railgun
 * 2021/6/9 19:44
 * PS: 中介代理
 **/
public class IntermediaryProxyImpl implements RentService {

    private static final Double RATIO = 0.8;

    private HostImpl hostImpl;

    public IntermediaryProxyImpl() {
    }

    public IntermediaryProxyImpl(HostImpl hostImpl) {
        this.hostImpl = hostImpl;
    }

    @Override
    public void rent() {
        // 联系
        contact();
        // 看房
        showHouse();
        // 签合同
        contract();
        hostImpl.rent();
    }

    @Override
    public void getRentExpenses(double cost) {
        cost = hostImpl.getCost();
        // 扣掉部分费用给房东
        charge(cost * (1 - RATIO));
        hostImpl.getRentExpenses(cost * RATIO);
    }

    private void contact() {
        System.out.println("联系租房人");
    }

    private void showHouse() {
        System.out.println("看房");
    }

    private void contract() {
        System.out.println("合同");
    }

    /**
     * railgun
     * 2021/6/9 19:53
     * PS: 收取中介费用
     **/
    private void charge(double cost) {
        System.out.println("收费：{}" + cost);
    }

}
