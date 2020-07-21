package com.fanbo.app.feign;

import com.fanbo.beans.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//指定要调哪个服务，这里要调的是fanbo-order-service服务
@FeignClient(value = "fanbo-order-service")
public interface OrderFeign {

    //Feign 书写方式和SpringMVC接口形式一样
    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(@RequestBody UserInfo userInfo);

    @RequestMapping("/feignTimeOutTest")
    public String feignTimeOutTest();

}
