package com.frame.spring.mybatis.dao;

import com.frame.spring.mybatis.pojo.UserTest;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/10 22:22
 * PS:
 **/
public interface UserTestMapper {
    /**
     * PS: 依据主键查询
     *
     * @param id 主键
     * @Author: railgun
     * @return: com.frame.spring.mybatis.pojo.UserTest
     * 2021/6/10 22:23
     **/
    UserTest selectUserTestById(String id);

    /**
     * PS:
     * @param id
     * @Author: railgun
     * @return: List<UserTest>
     * 2021/6/10 22:33
     **/
    List<UserTest> selectUserTestAll();
}
