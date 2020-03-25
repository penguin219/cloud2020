package com.wwy.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//ribbon实现负载均衡
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //需要这个注解开启负载均衡，提供service name以后知道去哪里找，默认是轮训机制
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
