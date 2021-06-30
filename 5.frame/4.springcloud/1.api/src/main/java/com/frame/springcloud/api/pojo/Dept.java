package com.frame.springcloud.api.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: railgun
 * 2021/6/30 23:55
 * PS: 部门
 * 支持链式编程
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {

    /**
     * 2021/6/30 23:56 @railgun 主键
     **/
    private Integer id;
    private String deptName;
    private String dbSource;

    public Dept(String deptName) {
        this.deptName = deptName;
    }

}
