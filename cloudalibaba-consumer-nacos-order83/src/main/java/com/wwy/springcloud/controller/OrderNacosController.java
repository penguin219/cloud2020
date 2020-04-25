package com.wwy.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    //实现代码与配置文件分离
    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/nacos/{id}")
    public String orderNacos(@PathVariable("id") Integer id){
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }
}
