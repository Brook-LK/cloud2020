package com.lucky.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringCloudApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args){
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
