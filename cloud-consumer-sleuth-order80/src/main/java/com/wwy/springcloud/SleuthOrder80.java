package com.wwy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SleuthOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(SleuthOrder80.class,args);
    }
}
