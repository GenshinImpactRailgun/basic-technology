package com.frame.spring.proxy.demo1;

/**
 * @Author: railgun
 * 2021/6/9 19:41
 * PS: 房东【公共方法】
 **/
public class HostImpl implements RentService {

    private static final Double COST = 4000D;

    public Double getCost() {
        return COST;
    }

    @Override
    public void rent() {
        System.out.println("有一个房子对外出租，此方法提供钥匙");
    }

    @Override
    public void getRentExpenses(double cost) {
        System.out.println("收取租金：" + cost);
    }

}
