package com.frame.springboot.mybatis.service.inter;

import com.frame.springboot.mybatis.pojo.Department;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/24 15:07
 * PS: 部门
 **/
public interface DepartmentService {
    /**
     * PS: 获取所有的部门
     *
     * @throws Exception:
     * @Author: railgun
     * @return: java.util.List<com.frame.springboot.mybatis.pojo.Department>
     * 2021/6/24 11:03
     **/
    List<Department> getDepartments();

    /**
     * PS: 通过id获得部门
     *
     * @param id
     * @throws Exception:
     * @Author: railgun
     * @return: com.frame.springboot.mybatis.pojo.Department
     * 2021/6/24 11:04
     **/
    Department getDepartment(Integer id);
}
