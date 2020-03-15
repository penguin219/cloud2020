package com.wwy.springcloud.controller;

import com.wwy.springcloud.entities.CommonResult;
import com.wwy.springcloud.entities.Payment;
import com.wwy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    //如果这里写成POST 使用浏览器无法访问，浏览器默认支持GET访问，使用postman可以
    //如果浏览器使用post，需要前端页面指定表单的提交方式为post，并且参数添加@RequestParam
    //@RequestBody 如果有其他consumer调用这个服务，就必须设置请求体，不然传递参数值为空
    @PostMapping(value="/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("【插入结果】："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功",payment);
        }else{
            return new CommonResult(444,"插入数据库失败");
        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("【查询结果】："+payment);
        if(payment!=null){
            return new CommonResult(200,"查询数据库成功",payment);
        }else{
            return new CommonResult(444,"查询数据库失败");
        }
    }

}
