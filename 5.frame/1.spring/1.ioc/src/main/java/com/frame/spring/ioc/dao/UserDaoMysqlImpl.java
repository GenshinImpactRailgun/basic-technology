package com.frame.spring.ioc.dao;

/**
 * @Author: railgun
 * 2021/6/1 0:01
 * PS:
 **/
public class UserDaoMysqlImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("获取 MySQL 用户");
    }
}
