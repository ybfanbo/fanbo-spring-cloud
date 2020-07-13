package com.fanbo.user.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {

    @Bean
    @LoadBalanced  //使用rest主式以别名方式调用依赖ribbon负载均衡器，必须使用@LocalBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
