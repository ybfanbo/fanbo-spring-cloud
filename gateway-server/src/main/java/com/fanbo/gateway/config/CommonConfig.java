package com.fanbo.gateway.config;


import com.fanbo.gateway.filter.RequestTimeFilter;
import com.fanbo.gateway.resolver.HostAddrKeyResolver;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;

@Configuration
public class CommonConfig {

    @Bean
    public HostAddrKeyResolver hostAddrKeyResolver() {
        //限流解析器（通过IP限流）
        return new HostAddrKeyResolver();
    }


    //将过滤器RequestTimeFilter注册到router中。（也可以通过application.yml配置）
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/app-api/**")
                        .filters(f -> f.filter(new RequestTimeFilter())  //自定义过滤器，打印出接口和消耗时间
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar")
                                .stripPrefix(1))  //路径前端要不要去掉之后再映射
                        .uri("lb://fanbo-app-api/")
                        .order(0)    //优先级，数字越小优先级越高
                        .id("app-api"))
                .route(r -> r.path("/xxxx-api/**")
                        .uri("lb://fanbo-xxxx-api/")
                        .order(0)
                        .id("xxxx-api"))
                .build();
    }


}
