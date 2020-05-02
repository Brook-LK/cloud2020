package com.lucky.springcloud.web;

import com.lucky.springcloud.entity.CommonResult;
import com.lucky.springcloud.entity.Payment;
import com.lucky.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入成功"+serverPort,result);
        }else{
            return new CommonResult(434,"插入失败"+serverPort);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getById(@PathVariable("id") long id){
        Payment payment = paymentService.getPayment(id);
        log.info("查询结果："+ payment);
        if(payment != null){
            return new CommonResult(200,"查询成功"+serverPort, payment);
        }else{
            return new CommonResult(404,"没有对应记录，查询ID"+serverPort+id);
        }
    }

    @GetMapping(value = "/lb")
    public String getServerPort(){
        return serverPort;
    }
}
