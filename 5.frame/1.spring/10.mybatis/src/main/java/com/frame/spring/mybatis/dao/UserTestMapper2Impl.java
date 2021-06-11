package com.frame.spring.mybatis.dao;

import com.frame.spring.mybatis.pojo.UserTest;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/11 12:32
 * PS: 接口实现类
 **/
public class UserTestMapper2Impl extends SqlSessionDaoSupport implements UserTestMapper {

    @Override
    public UserTest selectUserTestById(String id) {
        return getSqlSession().getMapper(UserTestMapper.class).selectUserTestById(id);
    }

    @Override
    public List<UserTest> selectUserTestAll() {
        return getSqlSession().getMapper(UserTestMapper.class).selectUserTestAll();
    }

}
