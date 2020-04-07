package com.wwy.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 统一对feign接口中的每个函数进行fallback处理
 * 如果这里也有，controller也单独做了设置，优先用单独设置的
 */
@Component
public class PaymentFallbackService  implements  PaymentService{

    @Override
    public String paymentInfo_ok(Integer id) {
        return "--PaymentFallbackService  paymentInfo_ok--";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "--PaymentFallbackService  paymentInfo_Timeout--";
    }
}
