package com.fanbo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserService {

    @Autowired
    private RestTemplate restTemplate;  //Sprint Boot Web组件，默认整合ribbon负载均衡器（底层使用httpClient）


    @RequestMapping("/getUser")
    public String getUser(){

        String url = "http://fanbo-user-dao/getUser";

        String daoResult = restTemplate.getForObject(url, String.class);

        return daoResult + "  通过RestTemplate方式获取用户信息, UserService 返回 OK！";
    }

    @RequestMapping("/getThreadName")
    public String getThreadName(){
        String threadName = Thread.currentThread().getName();
        return "当前线程池名称: " + threadName;
    }

}
