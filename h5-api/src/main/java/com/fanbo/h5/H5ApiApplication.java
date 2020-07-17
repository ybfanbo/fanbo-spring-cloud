package com.fanbo.h5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  //此服务将注册到注册中心
public class H5ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(H5ApiApplication.class, args);
    }

}
