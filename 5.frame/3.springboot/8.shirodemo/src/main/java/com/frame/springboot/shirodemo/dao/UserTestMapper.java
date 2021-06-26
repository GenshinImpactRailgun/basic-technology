package com.frame.springboot.shirodemo.dao;

import com.frame.springboot.shirodemo.pojo.UserTest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: railgun
 * 2021/6/26 13:55
 * PS: 用户接口
 **/
@Mapper
@Repository
public interface UserTestMapper {
    /**
     * PS: 依据用户名获取用户
     *
     * @param username 用户名
     * @Author: railgun
     * @return: com.frame.springboot.shirodemo.pojo.UserTest
     * 2021/6/26 13:56
     **/
    UserTest getUserByUsername(String username);
}
