package com.Notification_Service.Service;


import com.Notification_Service.Model.Customer_model;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class singleNode_singleConsumer_in_CG {

    //*************************Single Consumer String Object *******************//

    @KafkaListener(topics = "${kafka.topic.customer.string}",containerFactory = "stringKafkaListenerContainerFactory")
    public void consumeCustomerEvents1(ConsumerRecord<String, String> message) {
        int partition = message.partition();
        System.out.println("Group1  " + partition +" "+ message.value());

    }

    //*************************Single Consumer customer Object*******************//

    @KafkaListener(topics = "${kafka.topic.customer.object}",containerFactory = "customerKafkaListenerContainerFactory")
    public void MultipleconsumeCustomerEvents1(ConsumerRecord<String, Customer_model> message) {
        int partition = message.partition();
        System.out.println("Customer Consumer Group1 :----->> Partition " + partition + message.value());

    }



}
