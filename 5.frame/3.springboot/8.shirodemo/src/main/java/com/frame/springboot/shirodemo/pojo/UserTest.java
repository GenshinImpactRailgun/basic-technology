package com.frame.springboot.shirodemo.pojo;

import lombok.Data;

/**
 * @Author: railgun
 * 2021/6/26 13:44
 * PS: 用户
 **/
@Data
public class UserTest {
    private String id;
    private String username;
    private String alias;
    private String info;
    private String password;
    private String perms;
}
