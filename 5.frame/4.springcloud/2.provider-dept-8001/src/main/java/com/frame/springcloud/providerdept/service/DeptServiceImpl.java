package com.frame.springcloud.providerdept.service;

import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.providerdept.dao.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: railgun
 * 2021/7/1 0:34
 * PS: 部门接口实现类
 **/
@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    public Boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }

    public Dept queryById(Integer id) {
        return deptMapper.queryById(id);
    }

    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
