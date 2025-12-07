package com.OrderService.Controller;

import com.OrderService.Model.Orderproduct;
import com.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
public class Order {

    @Autowired
    OrderService orderservice;

    @Autowired
    KafkaTemplate<String, String> stringKafkaTemplate;

    //Mutiple Broker String consumer -->1 consumer in consumer Group
    //==================================================================
    @PostMapping("String_Mutibroker_placeorder")
    public String String_Mutibroker_placeorder(@RequestBody Map<String,String> req) {
        for (int i = 0; i < 1000; i++) {
            // Generate a completely random string
            String randomString = UUID.randomUUID().toString();
            String randomKey = UUID.randomUUID().toString();
            // Send to Kafka
            stringKafkaTemplate.send("order-events", randomKey,
                    "Order Created successfully for --> " + randomString);
        }

        return "Sent 1000 random messages";
    }



    @PostMapping("order_Multiplebroker_placeorder")
    public Orderproduct placeorder(@RequestBody  Map<String,Object> req)
    {
        System.out.println("*****"+req);
        Orderproduct res = orderservice.createOrder(req);
        return res;
    }
}
