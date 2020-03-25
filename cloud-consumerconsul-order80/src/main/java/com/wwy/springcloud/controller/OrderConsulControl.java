package com.wwy.springcloud.controller;

import com.wwy.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulControl {

    //zookeeper 和 eureka 命名方式一样
    public static final String PAYMENT_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(Payment payment){
        String string = restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
        return string;
    }

}
