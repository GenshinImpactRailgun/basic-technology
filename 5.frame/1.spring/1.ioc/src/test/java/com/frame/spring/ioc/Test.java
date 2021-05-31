package com.frame.spring.ioc;

import com.frame.spring.ioc.dao.UserDaoMysqlImpl;
import com.frame.spring.ioc.service.UserServiceImpl;

/**
 * @Author: railgun
 * 2021/5/31 23:56
 * PS:
 **/
public class Test {

    /**
     * railgun
     * 2021/6/1 0:06
     * PS: 相当于控制层的代码
     **/
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDaoIoc(new UserDaoMysqlImpl());
        userService.getUser();
    }

}
