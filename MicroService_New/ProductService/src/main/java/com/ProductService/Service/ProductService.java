package com.ProductService.Service;

import com.ProductService.Model.Product_model;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public Product_model saveproduct(Product_model product);

    Product_model getproduct(Integer id);

    List<Product_model> getproducts(List<Integer> data);
}
