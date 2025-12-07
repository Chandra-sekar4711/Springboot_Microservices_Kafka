package com.OrderService.Feign;

import com.OrderService.Model.Product_model;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;


@FeignClient("PRODUCT-SERVICE")
public interface Productfeign {

    @GetMapping("/getproduct/{id}")
    public Product_model getproduct(@PathVariable Integer id);

    @PostMapping("/getproducts")
    public List<Product_model> getproducts(@RequestBody Map<String, List<Integer>> req);


}
