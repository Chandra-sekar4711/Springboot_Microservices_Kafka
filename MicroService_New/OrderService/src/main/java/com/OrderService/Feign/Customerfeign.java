package com.OrderService.Feign;

import com.OrderService.Util.Customer_model;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CUSTOMER-SERVICE")
public interface Customerfeign {

    @GetMapping("/getuser/{id}")
    public Customer_model getuser(@PathVariable Integer id);

}
