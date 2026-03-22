package com.order_service.order.Client;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

public interface InventoryClient {

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    Boolean inStock(@RequestParam String skuCode,
                    @RequestParam Integer quantity);

    default Boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
        System.out.println("Fallback triggered for " + skuCode + " reason: " + throwable.getMessage());
        return false;
    }
}