package com.frame.spring.transaction.dao;

import com.frame.spring.transaction.pojo.UserTest;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/11 18:54
 * PS:
 **/
public class UserTestMapperImpl extends SqlSessionDaoSupport implements UserTestMapper {
    @Override
    public UserTest selectUserTestById(String id) {
        return getSqlSession().getMapper(UserTestMapper.class).selectUserTestById(id);
    }

    @Override
    public List<UserTest> selectUserTestAll() {
        return getSqlSession().getMapper(UserTestMapper.class).selectUserTestAll();
    }

    @Override
    public Integer insertUserTest(UserTest userTest) {
        return getSqlSession().getMapper(UserTestMapper.class).insertUserTest(userTest);
    }

    @Override
    public Integer deleteUserTestById(Integer id) {
        return getSqlSession().getMapper(UserTestMapper.class).deleteUserTestById(id);
    }
}
