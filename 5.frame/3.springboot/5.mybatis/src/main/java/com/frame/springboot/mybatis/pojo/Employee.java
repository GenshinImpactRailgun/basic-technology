package com.frame.springboot.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: railgun
 * 2021/6/24 13:46
 * PS: 雇员
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String lastName;
    private String email;

    /**
     * 2021/6/24 13:46 @railgun 1 male, 0 female
     **/
    private Integer gender;
    private Integer department;
    private Date birth;

    /**
     * 2021/6/24 13:46 @railgun 冗余设计
     **/
    private Department eDepartment;

}
