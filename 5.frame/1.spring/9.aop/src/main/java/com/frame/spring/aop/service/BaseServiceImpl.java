package com.frame.spring.aop.service;

/**
 * @Author: railgun
 * 2021/6/9 20:40
 * PS: 接口实现
 **/
public class BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public void insert() {
        System.out.println("业务方法：新增");
    }

    @Override
    public void delete() {
        System.out.println("业务方法：删除");
    }

    @Override
    public void update() {
        System.out.println("业务方法：更新");
    }

    @Override
    public void select() {
        System.out.println("业务方法：查询");
    }

    @Override
    public T findById() {
        return (T) new Object();
    }

}
