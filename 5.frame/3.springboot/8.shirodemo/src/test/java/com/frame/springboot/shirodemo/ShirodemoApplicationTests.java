package com.frame.springboot.shirodemo;

import com.basic.comon.util.GsonUtil;
import com.frame.springboot.shirodemo.service.inter.UserTestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ShirodemoApplicationTests {

    @Autowired
    private UserTestService userTestService;

    @Test
    public void test() {
        log.info(GsonUtil.objectToJson(userTestService.getUserByUsername("Kiana Kaslana")));
    }

}
