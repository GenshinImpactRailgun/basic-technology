package com.frame.spring.proxy.demo1;

/**
 * @Author: railgun
 * 2021/6/9 19:39
 * PS: 公共接口
 **/
public interface RentService {
    /**
     * PS: 出租，也可以理解为给钥匙
     *
     * @Author: railgun
     * @return: void
     * 2021/6/9 19:40
     **/
    void rent();

    /**
     * PS: 收取房租费用
     *
     * @param cost 费用
     * @Author: railgun
     * @return: void
     * 2021/6/9 20:03
     **/
    void getRentExpenses(double cost);
}
