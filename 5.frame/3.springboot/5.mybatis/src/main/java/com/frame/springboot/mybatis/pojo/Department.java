package com.frame.springboot.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: railgun
 * 2021/6/24 11:01
 * PS: 部门
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {


    private Integer id;

    private String departmentName;

}
