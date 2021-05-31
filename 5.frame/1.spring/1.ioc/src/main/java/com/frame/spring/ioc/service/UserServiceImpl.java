package com.frame.spring.ioc.service;

import com.frame.spring.ioc.dao.UserDao;
import com.frame.spring.ioc.dao.UserDaoImpl;

/**
 * @Author: railgun
 * 2021/6/1 0:02
 * PS:
 **/
public class UserServiceImpl implements UserService {

    /**
     * 2021/6/1 0:02 @railgun 控制权为业务逻辑层所有
     **/
    private UserDao userDao = new UserDaoImpl();

    /**
     * 2021/6/1 0:03 @railgun ioc 控制反转
     **/
    private UserDao userDaoIoc;

    @Override
    public void getUser() {
        userDao.getUser();
        userDaoIoc.getUser();
    }

    /**
     * railgun
     * 2021/6/1 0:04
     * PS: 设置对象
     **/
    public void setUserDaoIoc(UserDao userDao) {
        this.userDaoIoc = userDao;
    }

}
