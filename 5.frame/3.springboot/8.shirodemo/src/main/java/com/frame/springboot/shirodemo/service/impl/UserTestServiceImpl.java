package com.frame.springboot.shirodemo.service.impl;

import com.frame.springboot.shirodemo.dao.UserTestMapper;
import com.frame.springboot.shirodemo.pojo.UserTest;
import com.frame.springboot.shirodemo.service.inter.UserTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: railgun
 * 2021/6/26 13:59
 * PS:
 **/
@Slf4j
@Service
public class UserTestServiceImpl implements UserTestService {

    private final UserTestMapper userTestMapper;

    public UserTestServiceImpl(UserTestMapper userTestMapper) {
        this.userTestMapper = userTestMapper;
    }

    @Override
    public UserTest getUserByUsername(String username) {
        return userTestMapper.getUserByUsername(username);
    }
}
