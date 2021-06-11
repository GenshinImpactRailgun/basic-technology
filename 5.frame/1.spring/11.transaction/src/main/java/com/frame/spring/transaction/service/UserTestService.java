package com.frame.spring.transaction.service;

import com.frame.spring.transaction.pojo.UserTest;

/**
 * @Author: railgun
 * 2021/6/11 19:06
 * PS:
 **/
public interface UserTestService {
    /**
     * PS: 插入并删除用户
     *
     * @param userTest
     * @throws Exception:
     * @Author: railgun
     * @return: void
     * 2021/6/11 19:52
     **/
    void insertAndDelete(UserTest userTest);
}
