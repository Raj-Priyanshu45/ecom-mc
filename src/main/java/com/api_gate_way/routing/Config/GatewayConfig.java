package com.api_gate_way.routing.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Product Service
                .route("product-service", r -> r
                        .path("/api/products/**")
                        .filters(f -> f.circuitBreaker(c -> c
                                .setName("productCb")
                                .setFallbackUri("forward:/fallback/product")
                        ))
                        .uri("http://localhost:8080"))
                .route("product-service-swagger", r -> r
                        .path("/aggregate/product-service/api-docs")
                        .filters(f -> f.rewritePath(
                                "/aggregate/product-service/(?<segment>.*)",
                                "/${segment}"))
                        .uri("http://localhost:8080"))
                // Inventory Service
                .route("inventory-service", r -> r
                        .path("/api/inventory/**")
                        .filters(f -> f.circuitBreaker(c -> c
                                .setName("inventoryCB")
                                .setFallbackUri("forward:/fallback/inventory")))
                        .uri("http://localhost:8082"))
                .route("inventory-service-swagger", r -> r
                        .path("/aggregate/inventory-service/api-docs")
                        .filters(f -> f.rewritePath(
                                "/aggregate/inventory-service/(?<segment>.*)",
                                "/${segment}"))
                        .uri("http://localhost:8082"))
                // Order Service
                .route("order-service", r -> r
                        .path("/api/order/**")
                        .filters(f -> f.circuitBreaker(c -> c
                                .setName("orderCB")
                                .setFallbackUri("forward:/fallback/order")))
                        .uri("http://localhost:8081"))
                .route("order-service-swagger", r -> r
                        .path("/aggregate/order-service/api-docs")
                        .filters(f -> f.rewritePath(
                                "/aggregate/order-service/(?<segment>.*)",
                                "/${segment}"))
                        .uri("http://localhost:8081"))
                .route("fallback-route", r -> r
                    .path("/**")
                    .and()
                    .not(p -> p.path("/fallback/**"))
                    .filters(f -> f
                            .setStatus(404)
                            .modifyResponseBody(String.class, String.class,
                                    (exchange, body) -> Mono.just(
                                            "{\"message\":\"Route not found. Try again later\",\"status\":404}"
                                    )
                            )
                    )
                    .uri("no://op"))
            .build();
    }
}