package com.ecom_app.product_service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom_app.product_service.DTO.ProductRequest;
import com.ecom_app.product_service.Model.Products;
import com.ecom_app.product_service.Repo.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public Products createProduct(ProductRequest request){

        Products product = Products.builder()
                            .name(request.name())
                            .description(request.description())
                            .price(request.price())
                            .build();

        
        
        logger.info("Product Created Successfully");

        return productRepo.save(product);
    }

    public Page<Products> getPageOfProducts(int pageNumber , int pageSize){

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return productRepo.findAll(pageable);
    }

    public Page<Products> getPagewithSorting(int pageNumber , int pageSize , String field){

        Sort sort = Sort.by(Sort.Direction.DESC, field);

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return productRepo.findAll(pageable);
    }
}
