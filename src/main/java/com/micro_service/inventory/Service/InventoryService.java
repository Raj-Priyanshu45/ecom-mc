package com.micro_service.inventory.Service;

import org.springframework.stereotype.Service;

import com.micro_service.inventory.Repo.InventoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
    
    private final InventoryRepo inventoryRepo;

    public boolean inInStock(String skuCode , Integer quantity){

        return inventoryRepo.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);

    }
}
