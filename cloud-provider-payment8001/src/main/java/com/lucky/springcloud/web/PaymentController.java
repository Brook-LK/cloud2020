package com.lucky.springcloud.web;

import com.lucky.springcloud.entity.CommonResult;
import com.lucky.springcloud.entity.Payment;
import com.lucky.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
//@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "payment/add")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入成功"+serverPort,result);
        }else{
            return new CommonResult(434,"插入失败"+serverPort);
        }
    }

    @GetMapping(value = "payment/get/{id}")
    public CommonResult getById(@PathVariable("id") long id){
        Payment payment = paymentService.getPayment(id);
        log.info("查询结果："+ payment);
        if(payment != null){
            return new CommonResult(200,"查询成功"+serverPort, payment);
        }else{
            return new CommonResult(404,"没有对应记录，查询ID"+serverPort+id);
        }
    }

    @GetMapping(value = "payment/discovery")
    public Object discovery(){
        List<String> service = discoveryClient.getServices();
        for(String element : service){
            log.info("******element: " + element);
        }
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : serviceInstances){
            log.info("******instance: " + instance.getServiceId()+ "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "payment/lb")
    public String getServerPort(){
        return serverPort;
    }

    @GetMapping(value = "payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin(){
        return "spring zipkin";
    }
}
