package com.mq.kafka.springboot.autoconfigure.util;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: railgun
 * 2021/9/23 0:09
 * PS: import 用法二
 */
public class RailgunImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.mq.kafka.springboot.autoconfigure.util.ImportTest1", "com.mq.kafka.springboot.autoconfigure.util.ImportTest2"};
    }
}
