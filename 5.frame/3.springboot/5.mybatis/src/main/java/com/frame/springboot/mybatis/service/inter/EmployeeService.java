package com.frame.springboot.mybatis.service.inter;

import com.frame.springboot.mybatis.pojo.Employee;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/24 15:06
 * PS: 雇员
 **/
public interface EmployeeService {
    /**
     * PS:
     *
     * @throws Exception:
     * @Author: railgun
     * @return: List<Employee>
     * 2021/6/24 13:55
     **/
    List<Employee> getEmployees();

    /**
     * PS: 新增一个员工
     *
     * @param employee
     * @throws Exception:
     * @Author: railgun
     * @return: int
     * 2021/6/24 13:55
     **/
    int save(Employee employee);

    /**
     * PS: 通过id获得员工信息
     *
     * @param id
     * @throws Exception:
     * @Author: railgun
     * @return: com.frame.springboot.mybatis.pojo.Employee
     * 2021/6/24 13:55
     **/
    Employee get(Integer id);

    /**
     * PS: 通过id删除员工
     *
     * @param id
     * @throws Exception:
     * @Author: railgun
     * @return: int
     * 2021/6/24 13:55
     **/
    int delete(Integer id);
}
