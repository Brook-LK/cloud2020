package com.lucky.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderMainF80 {
    public static void main(String[] args){
        SpringApplication.run(OrderMainF80.class, args);
    }
}
