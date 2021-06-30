package com.frame.springcloud.providerdept;

import com.basic.comon.util.GsonUtil;
import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.providerdept.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProviderDeptApplicationTests {
    @Autowired
    private DeptService deptService;

    @Test
    public void test() {
        deptService.addDept(new Dept().setDeptName("railgun").setDbSource("db-1"));
        deptService.addDept(new Dept().setDeptName("超电磁炮").setDbSource("db-2"));
        GsonUtil.objectSoutJson(deptService.queryAll());
    }
}
