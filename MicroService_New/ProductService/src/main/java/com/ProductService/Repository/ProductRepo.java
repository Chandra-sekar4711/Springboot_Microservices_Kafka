package com.ProductService.Repository;

import com.ProductService.Model.Product_model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product_model,Integer> {
}
