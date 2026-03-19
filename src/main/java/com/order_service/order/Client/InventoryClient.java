package com.order_service.order.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="Inventory" , url="${inventory.url}")
public interface InventoryClient {
    
    @RequestMapping(method=RequestMethod.GET , value = "/api/inventory")
    Boolean inStock(@RequestParam String skuCode ,
                    @RequestParam Integer quantity) ;
}
