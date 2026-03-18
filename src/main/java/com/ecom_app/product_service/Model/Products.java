package com.ecom_app.product_service.Model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Document(value="product")
public class Products {
    
    @Id
    private String id;

    private String name;

    private String description;

    private BigDecimal price;
}
