package com.lucky.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    //用于访问其他服务器的接口
    @Bean
    //@LoadBalanced    //使用LoadBalanced开启RestTemplate的轮咨负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
