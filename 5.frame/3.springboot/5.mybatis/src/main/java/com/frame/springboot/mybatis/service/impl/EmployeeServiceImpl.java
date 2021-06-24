package com.frame.springboot.mybatis.service.impl;

import com.frame.springboot.mybatis.dao.EmployeeMapper;
import com.frame.springboot.mybatis.pojo.Employee;
import com.frame.springboot.mybatis.service.inter.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: railgun
 * 2021/6/24 15:06
 * PS:
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }

    @Override
    public int save(Employee employee) {
        return employeeMapper.save(employee);
    }

    @Override
    public Employee get(Integer id) {
        return employeeMapper.get(id);
    }

    @Override
    public int delete(Integer id) {
        return employeeMapper.delete(id);
    }
}
