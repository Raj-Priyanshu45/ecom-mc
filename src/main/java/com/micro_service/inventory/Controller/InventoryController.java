package com.micro_service.inventory.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro_service.inventory.Service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<?> isInStock(@RequestParam String skuCode , @RequestParam int quantity){

        return ResponseEntity.status(200).body(inventoryService.inInStock(skuCode , quantity));
    }
}
