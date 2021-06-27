package com.frame.springboot.swagger.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: railgun
 * 2021/6/27 11:49
 * PS: 用户
 **/
@Data
@ApiModel(value = "用户 dto")
public class UserDto {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "年龄")
    private int age;
}
