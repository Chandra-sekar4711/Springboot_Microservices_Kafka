package com.CustomerService.Repository;

import com.CustomerService.Model.Customer_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer_model,Integer> {
}
