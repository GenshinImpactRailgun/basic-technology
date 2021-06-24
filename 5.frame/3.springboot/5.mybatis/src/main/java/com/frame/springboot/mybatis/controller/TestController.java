package com.frame.springboot.mybatis.controller;

import com.frame.springboot.mybatis.pojo.Department;
import com.frame.springboot.mybatis.pojo.Employee;
import com.frame.springboot.mybatis.service.inter.DepartmentService;
import com.frame.springboot.mybatis.service.inter.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: railgun
 * 2021/6/24 11:11
 * PS: 控制层
 **/
@RestController
public class TestController {

    private final DepartmentService departmentService;

    private final EmployeeService employeeService;

    public TestController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }


    /**
     * railgun
     * 2021/6/24 11:14
     * PS: 查询全部部门
     **/
    @GetMapping("get-departments")
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    /**
     * railgun
     * 2021/6/24 11:14
     * PS: 依据主键查询部门
     **/
    @GetMapping("get-department/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentService.getDepartment(id);
    }

    /**
     * railgun
     * 2021/6/24 14:02
     * PS: 查询出所有的雇员
     **/
    @GetMapping("get-employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * railgun
     * 2021/6/24 14:04
     * PS: 保存
     **/
    @GetMapping("save")
    public int save() {
        Employee employee = new Employee();
        employee.setLastName("railgun");
        employee.setEmail("genshinimpactrailgun@gmail.com");
        employee.setGender(1);
        employee.setDepartment(1);
        employee.setBirth(new Date());
        return employeeService.save(employee);
    }

    /**
     * railgun
     * 2021/6/24 14:00
     * PS: 通过id获得员工信息
     **/
    @GetMapping("get/{id}")
    public Employee get(@PathVariable("id") Integer id) {
        return employeeService.get(id);
    }

    /**
     * railgun
     * 2021/6/24 14:00
     * PS: 通过id删除员工
     **/
    @GetMapping("delete/{id}")
    public int delete(@PathVariable("id") Integer id) {
        return employeeService.delete(id);
    }

}
