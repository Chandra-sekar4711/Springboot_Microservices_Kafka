package com.CustomerService.ServiceImpl;

import com.CustomerService.Model.Customer_model;
import com.CustomerService.Repository.CustomerRepo;
import com.CustomerService.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceimpl implements CustomerService {

    @Autowired
    CustomerRepo customerrepo;

    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Customer_model> customerKafkaTemplate;

//    @Override
//    public Customer_model createCustomer(Customer_model customer) {
//        Customer_model res = customerrepo.save(customer);
//        String key = res.getCname();
//        stringKafkaTemplate.send("customer-events",key,"Customer Created successfully for --> "+key);
//
//        return res;
//    }

    @Override
    public Customer_model createCustomer(Customer_model customer) {
        Customer_model res = customerrepo.save(customer);

        // Send the object to Kafka
        customerKafkaTemplate.send("customer-details", res.getCname(), res);

        return res;
    }

    @Override
    public Customer_model getuserByid(Integer id) {
        return customerrepo.findById(id).get();
    }


}
