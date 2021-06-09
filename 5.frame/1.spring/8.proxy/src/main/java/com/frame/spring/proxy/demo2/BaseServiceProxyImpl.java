package com.frame.spring.proxy.demo2;

import java.util.Date;

/**
 * @Author: railgun
 * 2021/6/9 20:38
 * PS: 接口实现代理——静态代理
 **/
public class BaseServiceProxyImpl<T> implements BaseService<T> {

    private BaseServiceImpl<T> baseService;

    public void setBaseService(BaseServiceImpl<T> baseService) {
        this.baseService = baseService;
    }

    @Override
    public void insert() {
        recordLogTime();
        baseService.insert();
    }

    @Override
    public void delete() {
        recordLogTime();
        baseService.delete();
    }

    @Override
    public void update() {
        recordLogTime();
        baseService.update();
    }

    @Override
    public void select() {
        recordLogTime();
        baseService.select();
    }

    @Override
    public T findById() {
        recordLogTime();
        return baseService.findById();
    }

    private void recordLogTime() {
        System.out.println("操作时间：" + new Date());
    }

}
