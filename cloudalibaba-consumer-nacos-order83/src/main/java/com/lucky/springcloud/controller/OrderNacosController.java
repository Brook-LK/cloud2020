package com.lucky.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;
    //public static final String SERVER_URL = "http://nacos-payment-provider";

        @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String getPayment(@PathVariable int id){
        return restTemplate.getForObject(serverURL + "/payment/nacos/" + id,String.class);
    }
}
