package com.fanbo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDaoApplication.class, args);
    }

}
