package com.order_service.order.Service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.order_service.order.Dto.OrderRequest;
import com.order_service.order.Model.Order;
import com.order_service.order.Repo.OrderRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepo orderRepo;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void placeRequest(OrderRequest request){

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(request.price());
        order.setSkuCode(request.skuCode());
        order.setQuantity(request.quantity());

        logger.info("Order placed successfully");

        orderRepo.save(order);
    }
}
