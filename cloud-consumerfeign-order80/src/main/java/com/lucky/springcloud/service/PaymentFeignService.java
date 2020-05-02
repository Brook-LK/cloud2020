package com.lucky.springcloud.service;

import com.lucky.springcloud.entity.CommonResult;
import com.lucky.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}
