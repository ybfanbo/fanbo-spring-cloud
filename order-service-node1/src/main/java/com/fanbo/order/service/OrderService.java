package com.fanbo.order.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService {

    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(){
        return "订单服务集群node1 返回的订单信息！111111111";
    }

    //feign客户端调用默认超时时间是1秒
    @RequestMapping("/feignTimeOutTest")
    public String feignTimeOutTest(){
        try {
            Thread.sleep(2000L);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "feignTimeOutTest node1 成功11111";
    }

}
