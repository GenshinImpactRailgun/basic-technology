package com.frame.springboot.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: railgun
 * 2021/6/23 22:39
 * PS: SpringBoot 测试类
 **/
@SpringBootTest
public class DataApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        System.out.println(dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
        // 如果有 template 可以拿来即 用，比如 jdbcTemplate、redisTemplate
    }

}
