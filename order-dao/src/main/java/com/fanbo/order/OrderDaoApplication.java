package com.fanbo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderDaoApplication.class, args);
    }

}
