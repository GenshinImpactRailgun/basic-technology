package com.java.jvm.service;

import com.java.jvm.entity.User;

/**
 * @Author: railgun
 * 2021/8/5 15:55
 * PS: 公共接口
 **/
public class BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public User findByForeignId(String key, Object procInsId) {
        System.out.printf("findByForeignId，key：%s，procInsId：%s%n", key, procInsId);
        return new User("446523", "railgun", 16, false);
    }

    @Override
    public User findListByForeignId(String key, String procInsId) {
        System.out.printf("findListByForeignId，key：%s，procInsId：%s%n", key, procInsId);
        return new User("16544", "Kuro-saki", 23, false);
    }

    @Override
    public void saveOrUpdate(Object object) {
        System.out.println(object);
    }

}
