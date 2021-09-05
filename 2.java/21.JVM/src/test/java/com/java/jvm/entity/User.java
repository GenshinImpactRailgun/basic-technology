package com.java.jvm.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private String userId;

    private String name;

    private Integer age;

    private Boolean whetherAdmin;

    public User() {
    }

    public User(String userId, String name, Integer age, Boolean whetherAdmin) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.whetherAdmin = whetherAdmin;
    }
}
