package com.frame.spring.ioc.dao;

/**
 * @Author: railgun
 * 2021/6/1 0:00
 * PS:
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("获取普通用户");
    }
}
