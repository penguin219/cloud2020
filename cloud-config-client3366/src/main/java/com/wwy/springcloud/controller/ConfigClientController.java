package com.wwy.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //实现动态配置刷新功能
/**
 * 实现不重启就能动态更新
 * 1.pom actuator
 * 2.yml 配置manage
 * 3.@RefreshScope
 * 4.http://localhost:3355/actuator/refresh 发POST请求进行刷新
 */
public class ConfigClientController {
    //访问Git上的配置信息
    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String port;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo+"  "+port;
    }
}
