package com.fanbo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaNode2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaNode2Application.class, args);
    }

}
