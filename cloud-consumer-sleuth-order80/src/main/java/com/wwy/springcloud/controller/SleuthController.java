package com.wwy.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class SleuthController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/order/zipkin")
    public String orderZipkin(){
        String result = restTemplate.getForObject("http://localhost:8001/payment/zipkin/",String.class);
        return result;
    }
}
