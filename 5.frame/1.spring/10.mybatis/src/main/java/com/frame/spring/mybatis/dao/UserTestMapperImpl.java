package com.frame.spring.mybatis.dao;

import com.frame.spring.mybatis.pojo.UserTest;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/11 12:32
 * PS: 接口实现类
 **/
public class UserTestMapperImpl implements UserTestMapper {

    private final SqlSessionTemplate sqlSessionTemplate;

    public UserTestMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public UserTest selectUserTestById(String id) {
        UserTestMapper mapper = sqlSessionTemplate.getMapper(UserTestMapper.class);
        return mapper.selectUserTestById(id);
    }

    @Override
    public List<UserTest> selectUserTestAll() {
        UserTestMapper mapper = sqlSessionTemplate.getMapper(UserTestMapper.class);
        return mapper.selectUserTestAll();
    }

}
