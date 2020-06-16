package com.lucky.springcloud.controller;

import com.lucky.springcloud.entity.CommonResult;
import com.lucky.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001/payment";
    //public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE" + "/payment";
    //CLOUD-PAYMENT-SERVICE相当于localhost:8001

    @Resource
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/add")
    public CommonResult<Payment> add(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/add", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL +"/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL +"/get/" + id, CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping(value = "payment/lb")
    public String getServerPort(){
        return restTemplate.getForObject(PAYMENT_URL + "/lb", String.class);
    }

    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin(){
        String result = restTemplate.getForObject(PAYMENT_URL + "/zipkin",String.class);
        return result;
    }

    @GetMapping(value = "/serverPort")
    public String getPort(){
        return serverPort;
    }
}
