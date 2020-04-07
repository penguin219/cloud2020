package com.wwy.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wwy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "paymentInfo_TimeOutHandler")
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @Value("$(server.port)")
    private String serverPort;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_ok(id);
        return result;
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    在80侧进行服务降级，为每个方法设置fallback
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties ={
            //设置自身调用最大时长，也就是5ms内正常逻辑，超过5ms走timeouthandler
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5")
    })

//    用全局fallback，配合类注解@DefaultProperties使用
//    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_Timeout(id);
    }

    // 若为全局fallback不能有参数，若是单独的参数类别要和函数一致
    public String paymentInfo_TimeOutHandler(Integer id){
        return "消费者80，我不等了";
    }
}
