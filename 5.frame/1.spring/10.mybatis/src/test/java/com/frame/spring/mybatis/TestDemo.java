package com.frame.spring.mybatis;

import com.basic.comon.util.GsonUtil;
import com.frame.spring.mybatis.dao.UserTestMapper;
import com.frame.spring.mybatis.pojo.UserTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: railgun
 * 2021/6/10 22:51
 * PS: 测试类
 **/
public class TestDemo {
    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession(true);
        UserTestMapper mapper = session.getMapper(UserTestMapper.class);
        List<UserTest> list = mapper.selectUserTestAll();
        list.forEach(GsonUtil::objectSoutJson);
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserTestMapper mapper = context.getBean("userTestMapper", UserTestMapper.class);
        List<UserTest> list = mapper.selectUserTestAll();
        list.forEach(GsonUtil::objectSoutJson);
    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserTestMapper mapper = context.getBean("userTestMapper2", UserTestMapper.class);
        List<UserTest> list = mapper.selectUserTestAll();
        list.forEach(GsonUtil::objectSoutJson);
    }

}
