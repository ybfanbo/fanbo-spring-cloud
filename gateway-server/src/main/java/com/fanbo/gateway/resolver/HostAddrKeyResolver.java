package com.fanbo.gateway.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

public class HostAddrKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        InetSocketAddress address = exchange.getRequest().getRemoteAddress();
        String hostName = address.getHostName();
        int port = address.getPort();
        System.out.println("限流了------hostName=" + hostName + " port=" + port);
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }


}
