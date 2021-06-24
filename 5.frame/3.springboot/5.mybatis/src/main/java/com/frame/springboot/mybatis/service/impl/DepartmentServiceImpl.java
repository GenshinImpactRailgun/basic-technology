package com.frame.springboot.mybatis.service.impl;

import com.frame.springboot.mybatis.dao.DepartmentMapper;
import com.frame.springboot.mybatis.pojo.Department;
import com.frame.springboot.mybatis.service.inter.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/24 15:07
 * PS: 部门
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }

    @Override
    public Department getDepartment(Integer id) {
        return departmentMapper.getDepartment(id);
    }
}
