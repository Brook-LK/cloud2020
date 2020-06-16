package com.lucky.springcloud.controller;

import com.lucky.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/serverPort")
    public String getServerPort(){
        return serverPort;
    }

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}
