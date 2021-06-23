package com.frame.springboot.data.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/6/23 23:04
 * PS:
 **/
@RestController
public class JdbcController {

    private final JdbcTemplate jdbcTemplate;

    public JdbcController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("insert")
    public void insert() {
        String sql = "insert into user_test (id, username, alias) values (5, 'spring', '靴子')";
        jdbcTemplate.execute(sql);
    }

    @GetMapping("delete")
    public void delete() {
        String sql = "delete from user_test where id = '4'";
        jdbcTemplate.execute(sql);
    }

    @GetMapping("update/{id}/{username}")
    public void update(@PathVariable(value = "id") String id, @PathVariable(value = "username") String username) {
        String sql = "update user_test set username = ? where id = ?";
        Object[] objects = new Object[2];
        objects[0] = username;
        objects[1] = id;
        jdbcTemplate.update(sql, objects);
    }

    @GetMapping("select")
    public List<Map<String, Object>> test() {
        String sql = "select * from user_test";
        return jdbcTemplate.queryForList(sql);
    }

}
