package com.wwy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wwy.springcloud.entities.CommonResult;
import com.wwy.springcloud.entities.Payment;
import com.wwy.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler="handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"test ok",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    //解决代码耦合，配置全局handler
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass= CustomerBlockHandler.class,blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"test ok",new Payment(2020L,"serial001"));
    }

    //程序运行异常 fallback
    @GetMapping("/byFallback/{id}")
    @SentinelResource(value = "byFallback",fallback = "handlerFallback")
    public CommonResult fallback(@PathVariable Integer id) {

        if(id == 1){
            throw new IllegalArgumentException("IllegalAccessException！！");
        }
        return new CommonResult(200,"test ok",new Payment(2020L,"serial001"));
    }

    public CommonResult handlerFallback(@PathVariable Integer id,Throwable throwable){
        return new CommonResult(444,"test error,fallback, "+ throwable.getMessage());
    }
}
