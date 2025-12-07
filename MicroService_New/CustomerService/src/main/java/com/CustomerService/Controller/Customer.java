package com.CustomerService.Controller;

import com.CustomerService.Model.Customer_model;
import com.CustomerService.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Customer {

    @Autowired
    CustomerService customerservice;

    @PostMapping("/CreateUser")
    public Customer_model CreateUser(@RequestBody Customer_model customer)
    {
        Customer_model res = customerservice.createCustomer(customer);
        return res;

    }

    @GetMapping("/getuser/{id}")
    public Customer_model getuser(@PathVariable Integer id) {
        Customer_model res = customerservice.getuserByid(id);
        return res;
    }

}
