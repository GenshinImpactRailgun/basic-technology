package com.frame.spring.transaction.service;

import com.frame.spring.transaction.dao.UserTestMapper;
import com.frame.spring.transaction.pojo.UserTest;

/**
 * @Author: railgun
 * 2021/6/11 19:06
 * PS:
 **/
public class UserTestServiceImpl implements UserTestService {
    private final UserTestMapper userTestMapper;

    public UserTestServiceImpl(UserTestMapper userTestMapper) {
        this.userTestMapper = userTestMapper;
    }

    @Override
    public void insertAndDelete(UserTest userTest) {
        userTestMapper.insertUserTest(userTest);
        userTestMapper.deleteUserTestById(userTest.getId());
    }
}
