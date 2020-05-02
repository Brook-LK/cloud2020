package com.lucky.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    //路由
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
         RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
         routes.route("path_route_lucky",
                 r -> r.path("/guonei")
                         .uri("http://news.baidu.com/guonei")).build();
        routes.route("path_route_lucky",
                r -> r.path("/guoji")
                        .uri("http://news.baidu.com/guoji")).build();
         return routes.build();
    }
}
