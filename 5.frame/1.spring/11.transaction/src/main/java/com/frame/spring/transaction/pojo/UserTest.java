package com.frame.spring.transaction.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: railgun
 * 2021/6/10 22:16
 * PS: 用户测试对象
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTest {

    private Integer id;
    private String username;
    private String alias;
    private String info;

}
