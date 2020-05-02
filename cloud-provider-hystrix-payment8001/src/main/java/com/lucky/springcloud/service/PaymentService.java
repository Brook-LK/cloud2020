package com.lucky.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    //正常访问
    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_OK,id  " + id;
    }

    //timeout,规定3秒中内为正常时间，超过报错，跳到兜底
    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeoutNumber = 5;
        try{
            TimeUnit.SECONDS.sleep(timeoutNumber);
            //TimeUnit.MILLISECONDS.sleep(5000);   //时间都是5秒，单位不同而已
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_Timeout,id  " + id +"  sleep: " + timeoutNumber + "s";
    }
    public String PaymentInfo_TimeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "  PaymentInfo_TimeoutHandler,id  " + id +"  sleep: 超时，启动兜底方法";
    }

    //-----服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")        //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("******id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();    //等价于UUID.randomUUID().toString()
        return Thread.currentThread().getName() + "调用成功，流水号：" + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能为负数，请输入正确的id后重试" + id;
    }
}
