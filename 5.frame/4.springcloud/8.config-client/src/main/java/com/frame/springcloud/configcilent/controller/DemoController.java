package com.frame.springcloud.configcilent.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/7/7 0:11
 * PS: 控制层
 **/
@RefreshScope
@RestController
public class DemoController {

    @Value("${server.port:}")
    private String serverPort;

    @Value("${whichRibbon:}")
    private String whichRibbon;

    @Value("${eureka.instance.instance-id:}")
    private String instanceId;

    @GetMapping("get-config")
    public Map<String, String> getConfig() {
        Map<String, String> map = new HashMap<>(6);
        map.put("serverPort", serverPort);
        map.put("whichRibbon", whichRibbon);
        map.put("instanceId", instanceId);
        return map;
    }
}
