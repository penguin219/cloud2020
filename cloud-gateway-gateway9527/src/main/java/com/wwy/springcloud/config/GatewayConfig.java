package com.wwy.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    //既可以在yml中进行配置，也可以在bean中进行配置
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //这个lambda表达式意思是，现在访问9527guonei会转发到后面的uri
        routes.route("path_route3",r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();

        return routes.build();
    }

}
