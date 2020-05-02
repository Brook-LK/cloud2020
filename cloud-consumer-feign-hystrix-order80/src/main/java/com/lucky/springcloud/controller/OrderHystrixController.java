package com.lucky.springcloud.controller;

import com.lucky.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
//    @HystrixCommand
    public String PaymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.PaymentInfo_OK(id);
        return "Order:80 return:" + result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "PaymentTimeoutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
//    @HystrixCommand
    public String PaymentInfo_Timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        String result = paymentHystrixService.PaymentInfo_Timeout(id);
        return "Order:80 return:" + result;
    }
    public String PaymentTimeoutFallbackMethod(@PathVariable("id") Integer id){
        return "80端口线程池：" + Thread.currentThread().getName() + "  PaymentTimeoutFallbackMethod,id  " + id +"  sleep: 超时，启动兜底方法";
    }
    //全局fallback
    public String payment_Global_FallbackMethod(@PathVariable("id") Integer id){
        return "全局降级，80端口线程池：" + Thread.currentThread().getName() + " ,id：  " + id +" 启动兜底方法";
    }

}
