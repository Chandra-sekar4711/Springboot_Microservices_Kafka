package com.CustomerService.Service;

import com.CustomerService.Model.Customer_model;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {


    Customer_model createCustomer(Customer_model customer);

    Customer_model getuserByid(Integer id);
}
