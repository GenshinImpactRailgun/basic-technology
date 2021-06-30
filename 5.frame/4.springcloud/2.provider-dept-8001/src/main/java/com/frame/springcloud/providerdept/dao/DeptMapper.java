package com.frame.springcloud.providerdept.dao;

import com.frame.springcloud.api.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: railgun
 * 2021/7/1 0:26
 * PS: 数据映射层
 **/
@Mapper
@Repository
public interface DeptMapper {

    /**
     * PS: 增加一个部门
     *
     * @param dept
     * @Author: railgun
     * @return: java.lang.Boolean
     * 2021/7/1 0:30
     **/
    Boolean addDept(Dept dept);

    /**
     * PS: 依据主键查询部门
     *
     * @param id
     * @Author: railgun
     * @return: com.frame.springcloud.api.pojo.Dept
     * 2021/7/1 0:30
     **/
    Dept queryById(Integer id);

    /**
     * PS: 查询所有部门
     *
     * @Author: railgun
     * @return: java.util.List<com.frame.springcloud.api.pojo.Dept>
     * 2021/7/1 0:31
     **/
    List<Dept> queryAll();
}
