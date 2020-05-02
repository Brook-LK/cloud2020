package com.lucky.springclouc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderZK {
    public static void main(String[] args){
        SpringApplication.run(OrderZK.class, args);
    }
}
