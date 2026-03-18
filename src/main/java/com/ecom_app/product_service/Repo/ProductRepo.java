package com.ecom_app.product_service.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom_app.product_service.Model.Products;

@Repository
public interface ProductRepo extends MongoRepository<Products, String>{
    
}
