package com.frame.spring.annotation.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: railgun
 * 2021/6/8 12:07
 * PS:
 **/
@Component
public class UserDto {

    @Value(value = "司马迁")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
