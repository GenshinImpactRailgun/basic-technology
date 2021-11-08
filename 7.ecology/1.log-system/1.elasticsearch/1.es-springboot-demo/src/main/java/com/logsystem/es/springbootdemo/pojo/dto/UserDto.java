package com.logsystem.es.springbootdemo.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author railgun
 * 2021/11/7 19:01
 * 用户 dto
 */
@Data
public class UserDto implements Serializable {

    /**
     * 2021/11/7 20:47 @railgun 主键
     */
    private String id;

    /**
     * 2021/11/7 19:02 @railgun 用户名
     */
    private String username;

    /**
     * 2021/11/7 19:02 @railgun 用户别名
     */
    private String alias;

}
