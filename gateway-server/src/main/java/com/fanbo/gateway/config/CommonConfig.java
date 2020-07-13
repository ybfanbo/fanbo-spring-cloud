package com.fanbo.gateway.config;


import com.fanbo.gateway.filter.RequestTimeFilter;
import com.fanbo.gateway.resolver.HostAddrKeyResolver;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
                        .filters(f -> f.filter(new RequestTimeFilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://fanbo-app-api/")
                        .order(1)
                        .id("customer_filter_router")
                )
                .build();
    }


}
