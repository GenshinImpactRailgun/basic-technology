package com.frame.springboot.shirodemo.service.inter;

import com.frame.springboot.shirodemo.pojo.UserTest;

/**
 * @Author: railgun
 * 2021/6/26 13:59
 * PS:
 **/
public interface UserTestService {
    /**
     * PS: 依据用户名获取用户
     *
     * @param username 用户名
     * @Author: railgun
     * @return: com.frame.springboot.shirodemo.pojo.UserTest
     * 2021/6/26 14:00
     **/
    UserTest getUserByUsername(String username);
}
