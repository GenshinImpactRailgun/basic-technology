package com.java.jvm.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class People {

    private String peopleId;

    private String name;

    private Integer age;

    private Boolean whetherAdmin;

    public People() {
    }

    public People(String peopleId, String name, Integer age, Boolean whetherAdmin) {
        this.peopleId = peopleId;
        this.name = name;
        this.age = age;
        this.whetherAdmin = whetherAdmin;
    }
}
