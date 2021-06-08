package com.frame.spring.configuration.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: railgun
 * 2021/6/8 12:30
 * PS:
 **/
@Component
public class UserDto {

    @Value(value = "司马光")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
