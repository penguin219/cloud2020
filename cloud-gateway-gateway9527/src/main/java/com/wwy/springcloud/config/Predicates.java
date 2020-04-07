package com.wwy.springcloud.config;

import java.time.ZonedDateTime;

/**
 * yml中predicates可以配置的内容
 */
public class Predicates {

    public static void main(String[] args) {
        //- After=时间
        //- Before=时间
        //- Between=时间1，时间1
        ZonedDateTime zbj = ZonedDateTime.now();
        System.out.println(zbj);//2020-04-07T14:19:49.223+08:00[Asia/Shanghai]

        //- Cookie=username,zzyy 表示一个键值对
        //还有其他header、host、method等等用到可以查看官网

    }
}
