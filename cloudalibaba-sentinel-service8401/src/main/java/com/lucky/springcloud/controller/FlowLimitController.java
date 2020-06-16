package com.lucky.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping(value = "/testA")
    public String testA(){
        return "*****testA";
    }

    @GetMapping(value = "/testB")
    public String testB(){
        return "*****testB";
    }

    @GetMapping(value = "/testD")
    public String testD(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "*****testD";
    }
}
