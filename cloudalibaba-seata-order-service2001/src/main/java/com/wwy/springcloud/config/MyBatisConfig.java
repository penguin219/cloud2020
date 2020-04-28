package com.wwy.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wwy.springcloud.dao")
public class MyBatisConfig {

}
