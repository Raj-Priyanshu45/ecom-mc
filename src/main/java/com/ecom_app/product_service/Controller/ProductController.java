package com.ecom_app.product_service.Controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_app.product_service.DTO.ProductRequest;
import com.ecom_app.product_service.Model.Products;
import com.ecom_app.product_service.Service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request){        

        return ResponseEntity.status(201).body(productService.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<Page<Products>> getAllProducts(
        @RequestParam(defaultValue="0") int pageNumber,
        @RequestParam(defaultValue="10") int pageSize
    ){
        return ResponseEntity.status(200).body(productService.getPageOfProducts(pageNumber , pageSize));
    }

    @GetMapping("/sort")
    public ResponseEntity<Page<Products>> getAllProductsWithSorting(
        @RequestParam(defaultValue="0") int pageNumber,
        @RequestParam(defaultValue="10") int pageSize , 
        @RequestParam String field
    ){
        return ResponseEntity.status(200).body(productService.getPagewithSorting(pageNumber , pageSize , field));
    }
}
