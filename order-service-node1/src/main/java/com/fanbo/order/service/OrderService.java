package com.fanbo.order.service;

import com.fanbo.beans.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService {

    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(@RequestBody UserInfo userInfo){
        return "订单服务集群node1 返回的订单信息！111111111" + " userAge=" + userInfo.getAge() + " userName=" + userInfo.getName();
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
