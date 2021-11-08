package com.logsystem.es.springbootdemo.pojo.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: railgun
 * 2021/10/19 23:11
 * PS: LOL 游戏人物数据对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lol implements Serializable {

    private Long id;

    /**
     * 英雄游戏名字
     */
    private String name;

    /**
     * 英雄名字
     */
    private String realName;

    /**
     * 英雄描述信息
     */
    private String desc;

}
