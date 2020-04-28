package com.wwy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "testB";
    }

    //测试Hotkey
    //SentinelResource是自定义兜底，加了SentinelResource在资源名称那里就填value值，不需要“/”
    @GetMapping("/testHotKey")
    @SentinelResource(value="testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p1",required = false) String p2){
        return "---------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "---------testHotKeyHandler";
    }
}
