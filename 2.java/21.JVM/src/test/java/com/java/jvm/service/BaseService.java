package com.java.jvm.service;

import com.java.jvm.entity.User;

import java.util.List;

/**
 * @Author: railgun
 * 2021/8/5 15:51
 * PS: 公共接口
 **/
public interface BaseService<T> {

    User findByForeignId(String key, Object procInsId);

    User findListByForeignId(String key, String procInsId);

    void saveOrUpdate(Object object);

}
