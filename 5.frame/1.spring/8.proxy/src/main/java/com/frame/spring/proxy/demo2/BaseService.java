package com.frame.spring.proxy.demo2;

/**
 * @Author: railgun
 * 2021/6/9 20:21
 * PS: 公共接口
 **/
public interface BaseService<T> {
    /**
     * PS: 新增
     *
     * @Author: railgun
     * @return: void
     * 2021/6/9 20:35
     **/
    void insert();

    /**
     * PS: 删除
     *
     * @Author: railgun
     * @return: void
     * 2021/6/9 20:36
     **/
    void delete();

    /**
     * PS: 更新
     *
     * @Author: railgun
     * @return: void
     * 2021/6/9 20:36
     **/
    void update();

    /**
     * PS: 查询
     *
     * @Author: railgun
     * @return: void
     * 2021/6/9 20:36
     **/
    void select();

    /**
     * PS:  依据id 查找
     *
     * @Author: railgun
     * @return: T
     * 2021/6/9 20:36
     **/
    T findById();
}
