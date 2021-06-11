package com.frame.spring.transaction.dao;

import com.frame.spring.transaction.pojo.UserTest;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/11 18:48
 * PS:
 **/
public interface UserTestMapper {
    /**
     * PS: 依据主键获取数据
     *
     * @param id
     * @throws Exception:
     * @Author: railgun
     * @return: com.frame.spring.transaction.pojo.UserTest
     * 2021/6/11 18:49
     **/
    UserTest selectUserTestById(String id);

    /**
     * PS: 获取所有数据
     *
     * @throws Exception:
     * @Author: railgun
     * @return: java.util.List<com.frame.spring.transaction.pojo.UserTest>
     * 2021/6/11 18:49
     **/
    List<UserTest> selectUserTestAll();

    /**
     * PS: 插入一个对象
     * @param userTest
     * @throws Exception:
     * @Author: railgun
     * @return: java.lang.Integer
     * 2021/6/11 19:08
     **/
    Integer insertUserTest(UserTest userTest);

    /**
     * PS: 
     * @param id  
     * @throws Exception: 
     * @Author: railgun
     * @return: java.lang.Integer
     * 2021/6/11 19:57
     **/
    Integer deleteUserTestById(Integer id);

}
