package com.wwy.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wwy.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用，自定义CustomHandler");
    }
    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用，自定义2CustomHandler");
    }
}
