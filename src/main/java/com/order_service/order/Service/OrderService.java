package com.order_service.order.Service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.order_service.order.Client.InventoryClient;
import com.order_service.order.Dto.OrderRequest;
import com.order_service.order.Model.Order;
import com.order_service.order.Repo.OrderRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepo orderRepo;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final InventoryClient inventoryClient;

    public void placeRequest(OrderRequest request){

        var isInStock = inventoryClient.inStock(request.skuCode(), request.quantity());

        if(isInStock){
            Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(request.price());
        order.setSkuCode(request.skuCode());
        order.setQuantity(request.quantity());

        logger.info("Order placed successfully");

        orderRepo.save(order);

        }else{
            throw  new RuntimeException("Product Unavailabe");
        }
    }

    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    public Boolean checkStock(String skuCode, Integer quantity) {
        return inventoryClient.inStock(skuCode, quantity);
    }

    public Boolean fallbackMethod(String skuCode, Integer quantity, Throwable t) {
        logger.info("Unable to get inventory for {} failure reason: {}" , skuCode , t.getMessage());
        return false;
    }
}
