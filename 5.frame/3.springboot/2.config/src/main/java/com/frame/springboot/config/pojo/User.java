package com.frame.springboot.config.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 * @Author: railgun
 * 2021/6/20 17:35
 * PS: 引入指定的配置文件
 **/
@Repository
@PropertySource(value = "classpath:user.yml")
public class User {
    @Value("${name:railgunDefault}")
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
