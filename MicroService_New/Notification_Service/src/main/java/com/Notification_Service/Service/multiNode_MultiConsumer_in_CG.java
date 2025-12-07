package com.Notification_Service.Service;

import com.Notification_Service.Model.Orderresponsemodel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class multiNode_MultiConsumer_in_CG {

    @KafkaListener(topics = "${kafka.topic.order.object}",containerFactory = "OrderKafkaListenerContainerFactory")
    public void MultipleconsumeCustomerEvents1(ConsumerRecord<String, Orderresponsemodel> message) {
        int partition = message.partition();
        System.out.println("Multi Broker Order multiple consumer  :----->> Partition "+ "*******" + partition +"*******" + message.value());

    }

}
