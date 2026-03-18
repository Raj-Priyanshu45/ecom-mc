package com.order_service.order.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order_service.order.Model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{
    
}
