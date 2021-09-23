package com.mq.kafka.springboot.autoconfigure.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: railgun
 * 2021/9/23 0:13
 * PS: 测试 @Import
 */
@Slf4j
@Configuration
@Import({ImportTest1.class, ImportTest2.class})
public class TestImport {

    public TestImport() {
        log.info("初始化了 TestImport");
    }

}
