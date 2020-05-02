package com.lucky.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    //定义自己的Rule
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
