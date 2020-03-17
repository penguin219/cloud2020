package com.wwy.springcloud.controller;

import com.wwy.springcloud.entities.CommonResult;
import com.wwy.springcloud.entities.Payment;
import com.wwy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    //服务发现，对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息
    @Resource
    private DiscoveryClient discoveryClient;

    //如果这里写成POST 使用浏览器无法访问，浏览器默认支持GET访问，使用postman可以
    //如果浏览器使用post，需要前端页面指定表单的提交方式为post，并且参数添加@RequestParam
    //@RequestBody 如果有其他consumer调用这个服务，就必须设置请求体，不然传递参数值为空
    @PostMapping(value="/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("【插入结果】："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功 "+serverPort,payment);
        }else{
            return new CommonResult(444,"插入数据库失败 "+serverPort);
        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("【查询结果】："+payment);
        if(payment!=null){
            return new CommonResult(200,"查询数据库成功 "+serverPort,payment);
        }else{
            return new CommonResult(444,"查询数据库失败 "+serverPort);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        //获取所有的服务名称
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("【service】: "+service);
        }

        //获取某一个服务的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info("【service instance】: "+instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

}
