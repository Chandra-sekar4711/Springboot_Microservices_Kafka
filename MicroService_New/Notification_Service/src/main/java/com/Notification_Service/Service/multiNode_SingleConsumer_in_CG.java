package com.Notification_Service.Service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@ Service
public class multiNode_SingleConsumer_in_CG {

    //*************************String Single Consumer*******************//

    @KafkaListener(topics = "${kafka.topic.order.string}",containerFactory = "stringKafkaListenerOrderFactory")
    public void consumeCustomerEvents1(ConsumerRecord<String, String> message) {
        int partition = message.partition();
        System.out.println("Multi Broker String Single consumer  :----->> Partition "+ "*******" + partition +"*******" + message.value());
    }
}
