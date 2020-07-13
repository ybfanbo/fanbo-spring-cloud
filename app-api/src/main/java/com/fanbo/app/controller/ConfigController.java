package com.fanbo.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/config")
@RefreshScope  //用于手动刷新配置，只要调取/actuator/refresh接口就可以了，这个接口是POST请求
public class ConfigController {

    //从配置文件中获取goodsId
    @Value("${goodsId}")
    private String goodsId;

    @RequestMapping(value = "/getGoodsId")
    public String getGoodsId(){
        return "从配置文件中获取到的goodsId：" + goodsId;
    }

}
