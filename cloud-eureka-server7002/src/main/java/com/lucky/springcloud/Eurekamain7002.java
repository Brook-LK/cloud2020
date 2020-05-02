package com.lucky.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eurekamain7002 {
    public static void main(String[] args){
        SpringApplication.run(Eurekamain7002.class, args);
    }
}
