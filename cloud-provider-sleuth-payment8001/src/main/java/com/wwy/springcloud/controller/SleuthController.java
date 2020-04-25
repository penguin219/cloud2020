package com.wwy.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SleuthController {

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "I'm sleuth from provider 8001.";
    }
}
