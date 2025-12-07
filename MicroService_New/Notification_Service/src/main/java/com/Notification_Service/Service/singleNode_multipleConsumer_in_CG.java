package com.Notification_Service.Service;


import com.Notification_Service.Model.Customer_model;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class singleNode_multipleConsumer_in_CG {

    // String
    //================================================================================

     //always allocated to Partition 0
    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "${kafka.topic.customer.string}", partitions = {"0"}),
            containerFactory = "stringKafkaListenerContainerFactoryGroup2"
    )
    public void consumerGroup2Partition2(ConsumerRecord<String, String> message) {
        System.out.println("Group2  " + message.partition() +" "+ message.value());    }


    //always allocated to Partition 2
    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "${kafka.topic.customer.string}", partitions = {"2"}),
            containerFactory = "stringKafkaListenerContainerFactoryGroup2"
    )
    public void consumerGroup2Partition3(ConsumerRecord<String, String> message) {
        System.out.println("Group2  " + message.partition() +" "+ message.value());
    }


    //===================================================================================================================================================
    //===================================================================================================================================================



    // Customer Object
    //*************************Multiple Consumer*******************//
    // It has 3 consumers that is defined in config

    @KafkaListener(topics = "${kafka.topic.customer.object}",containerFactory = "customerKafkaListenerContainerFactoryGroup2")
    public void MultipleconsumeCustomerEvents1(ConsumerRecord<String, Customer_model> message) {
        int partition = message.partition();
        System.out.println("Customer Consumer Group2 :----->> Partition " + partition + message.value());

    }



}
