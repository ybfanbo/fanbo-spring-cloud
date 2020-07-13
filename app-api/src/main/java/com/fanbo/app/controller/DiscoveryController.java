package com.fanbo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 从注册中心获取所有服务列表
 * */
@RestController
@RequestMapping(value = "/discovery")
public class DiscoveryController {


    @Autowired
    private DiscoveryClient discoveryClient;



    //获取注册中心上服务列表信息
    @RequestMapping("/getServers")
    public String discoveryClient(){

        StringBuilder result = new StringBuilder();

        List<String> services = discoveryClient.getServices();
        for (String service : services){
            System.out.println("service: " + service);
            result.append(service + "； ");
        }

        List<ServiceInstance> discoveryClients = discoveryClient.getInstances("fanbo-user-service");  //通过服务名称获取
        for (ServiceInstance serviceInstance : discoveryClients){
            String host = serviceInstance.getHost();
            int port = serviceInstance.getPort();

            System.out.println("host: " + host);
            System.out.println("port: " + port);
            System.out.println("uriPath: " + serviceInstance.getUri());

            result.append(host + ":" + port + "； ");
        }

        return result.toString();
    }

}
