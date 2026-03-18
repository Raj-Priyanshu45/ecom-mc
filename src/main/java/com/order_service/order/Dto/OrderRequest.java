package com.order_service.order.Dto;

import java.math.BigDecimal;

public record  OrderRequest (
    // Long id,
    // String orderNumber ,
    String skuCode ,
    BigDecimal price ,
    Integer quantity
){
    
}
