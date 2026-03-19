package com.micro_service.inventory.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro_service.inventory.Model.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long>{
    
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode , Integer quantity);
}
