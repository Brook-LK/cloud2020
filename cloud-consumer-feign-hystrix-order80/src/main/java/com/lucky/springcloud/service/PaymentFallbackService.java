package com.lucky.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String PaymentInfo_OK(Integer id) {
        return "---- PaymentFallbackService fall back -PaymentInfo_OK,o--o" + id;
    }

    @Override
    public String PaymentInfo_Timeout(Integer id) {
        return "---- PaymentFallbackService fall back - PaymentInfo_Timeout,o--o" + id;
    }
}
