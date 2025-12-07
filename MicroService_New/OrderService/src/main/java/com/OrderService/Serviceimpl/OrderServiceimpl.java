package com.OrderService.Serviceimpl;

import com.OrderService.Config.KafkaProducerConfig;
import com.OrderService.Feign.Customerfeign;
import com.OrderService.Feign.Productfeign;
import com.OrderService.Model.Orderproduct;
import com.OrderService.Model.Orderresponsemodel;
import com.OrderService.Model.Product_model;
import com.OrderService.Repository.OrderRepo;
import com.OrderService.Service.OrderService;
import com.OrderService.Util.Customer_model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    Customerfeign customerfeign;

    @Autowired
    Productfeign productfeign;

    @Autowired
    OrderRepo orderrepo;

    @Autowired
    KafkaTemplate<String, Orderresponsemodel> objectKafkaTemplate;

    //Mutiple broker Order Object Mutiple Consumer in a Consumer Group
    //===================================================================
    @Override
    public Orderproduct createOrder(Map<String,Object> req) {
        System.out.println("Customer id from request: " + req.get("Customer_id"));

        Customer_model res = customerfeign.getuser((Integer) req.get("Customer_id"));
        Orderproduct orderedproduct = null;
        if (res != null) {
            Map<String, List<Integer>> data = new HashMap<>();
            data.put("productid", (List<Integer>) req.get("productid"));
            List<Product_model> products = productfeign.getproducts(data);

            Orderproduct order = new Orderproduct();
            order.setCustomername(res.getCname());

            // Create new product instances and set the bidirectional relationship
            List<Product_model> managedProducts = products.stream()
                    .map(product -> {
                        Product_model newProduct = new Product_model();

                        newProduct.setPname(product.getPname());
                        newProduct.setCreatedAt(product.getCreatedAt());
                        newProduct.setPrice(product.getPrice());
                        newProduct.setCategory(product.getCategory());
                        newProduct.setInventoryCount(product.getInventoryCount());
                        newProduct.setOrderproduct_fk(order); // Set the bidirectional relationship
                        return newProduct;
                    })
                    .collect(Collectors.toList());

            order.setProductdetail(managedProducts);
            orderedproduct = orderrepo.save(order);

            Orderresponsemodel obj = new Orderresponsemodel();
            obj.setCustomerid(orderedproduct.getOrderid());
            obj.setOrderid(orderedproduct.getOrderid());
            obj.setCustomername(orderedproduct.getCustomername());
            objectKafkaTemplate.send("order-details",orderedproduct.getCustomername(),obj);

        }

        return orderedproduct;
    }}
