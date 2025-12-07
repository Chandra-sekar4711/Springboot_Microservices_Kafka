package com.ProductService.Controller;

import com.ProductService.Model.Product_model;
import com.ProductService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Product {

    @Autowired
    ProductService productservice;

    @PostMapping("/saveproduct")
    public Product_model saveproduct(@RequestBody Product_model product)
    {
        Product_model res = productservice.saveproduct(product);
        return res;
    }

    @GetMapping("/getproduct/{id}")
    public Product_model getproduct(@PathVariable Integer id)
    {
        Product_model res = productservice.getproduct(id);
        return res;
    }

    @PostMapping("/getproducts")
    public List<Product_model> getproducts(@RequestBody Map<String, List<Integer>> req)
    {
        List<Integer> data = (List<Integer>) req.get("productid");
        List<Product_model> res =  productservice.getproducts(data);
        return res;
    }
}
