package com.frame.springboot.config.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

/**
 * @Author: railgun
 * 2021/6/20 17:15
 * PS:
 **/

@Repository
@ConfigurationProperties(prefix = "dog")
public class Dog {

    @Value(value = "${name:狗子}")
    private String name;

    @Value(value = "${name-test:狗子}")
    private String nameTest;

    private String firstName;

    @Value(value = "${age:16}")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Dog() {
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", nameTest='" + nameTest + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
