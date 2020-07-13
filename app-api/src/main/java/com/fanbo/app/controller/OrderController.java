package com.fanbo.app.controller;

import com.fanbo.app.feign.OrderFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(){
        return "api调order服务返回的结果是：" + orderFeign.getOrderInfo();
    }

    @RequestMapping("/feignTimeOutTest")
    public String feignTimeOutTest(){
        return "api调order服务返回结果是：" + orderFeign.feignTimeOutTest();
    }

    @HystrixCommand(fallbackMethod = "orderFallbackMethod")
    @RequestMapping("/hystrixTest")
    public String hystrixTest(){
        String threadName = Thread.currentThread().getName();
        System.out.println("------------threadName=" + threadName);
        return "开启了Hystrix： threadName=" + threadName;
    }

    public String orderFallbackMethod(){
        return "订单服务繁忙，请稍后再试！";
    }

    @RequestMapping("/noHystrixTest")
    public String noHystrixTest(){
        String threadName = Thread.currentThread().getName();
        System.out.println("++++++++++++threadName=" + threadName);
        return "没有开启Hystrix： threadName=" + threadName;
    }


}
