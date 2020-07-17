package com.fanbo.h5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    //使用rest主式以别名方式调用依赖ribbon负载均衡器，必须使用@LocalBalanced
    //Sprint Boot Web组件，默认整合ribbon负载均衡器（底层使用httpClient）
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getUser")
    public String getUser(){

        String url = "http://fanbo-user-service/getUser";

        String result = restTemplate.getForObject(url, String.class);

        return "来自 h5-api " + result;
    }

    @RequestMapping("/getThreadName")
    public String getThreadName(){
        String url = "http://fanbo-user-service/getThreadName";
        String result = restTemplate.getForObject(url, String.class);
        return "调userService返回的结果: " + result;
        //调userService返回的结果: 当前线程池名称: http-nio-9000-exec-7    其中http-nio-9000-exec是线程池名称，7是线程id
        /**
         * 默认情况下，tomcat只有一个线程池处理所有请求
         * 配置最大线程数：
         * server:
         *   port: 8001
         *   tomcat:
         *     threads:
         *       max: 10
         * */
    }


}
