package com.Notification_Service.Config;

import com.Notification_Service.Model.Customer_model;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SingleNode_KafkaCustomerConfig {

    @Value("${kafka.topic.customer.string.group1}")
    private String string_my_group1;

    @Value("${kafka.topic.customer.string.group2}")
    private String string_my_group2;

    @Value("${kafka.topic.customer.object.group1}")
    private String customer_my_group1;

    @Value("${kafka.topic.customer.object.group2}")
    private String customer_my_group2;




    // 1️⃣ Consumer for String messages which has an 1 consumer group and 1 consumer in that which consumes data from all partition
    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9098");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, string_my_group1);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }

    // 1️⃣ Consumer for Consumer Object messages which has an 1 consumer group and 2 consumer in that which consumes data from 0,2 partition

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactoryGroup2() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9098");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, string_my_group2);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactoryGroup2() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactoryGroup2());
        return factory;
    }



    //===================================================== Consumer Object ===========================================================================

    // 2️⃣Consumer for Consumer messages which has an 1 consumer group and 1 consumer in that which consumes data from all partition
    // Consumer for Customermodel (JSON object)
    //================ Consumer Group 1 =================
    @Bean
    public ConsumerFactory<String, Customer_model> customerConsumerFactory() {
        JsonDeserializer<Customer_model> deserializer = new JsonDeserializer<>(Customer_model.class);
        deserializer.addTrustedPackages("*");
        deserializer.setRemoveTypeHeaders(false);
        deserializer.setUseTypeMapperForKey(false);
        deserializer.setUseTypeHeaders(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9098");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, customer_my_group1);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Customer_model> customerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Customer_model> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(customerConsumerFactory());
        return factory;
    }

    //================ Consumer Group 2 =================
    @Bean
    public ConsumerFactory<String, Customer_model> customerConsumerFactoryGroup2() {
        JsonDeserializer<Customer_model> deserializer = new JsonDeserializer<>(Customer_model.class);
        deserializer.addTrustedPackages("*");
        deserializer.setRemoveTypeHeaders(false);
        deserializer.setUseTypeMapperForKey(false);
        deserializer.setUseTypeHeaders(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9098");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, customer_my_group2);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Customer_model> customerKafkaListenerContainerFactoryGroup2() {
        ConcurrentKafkaListenerContainerFactory<String, Customer_model> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(customerConsumerFactoryGroup2());
        factory.setConcurrency(3);  // 3 consumers in the same group
        return factory;
    }


    //NOTE  :==>>  the above one are just a config for consumer group when we define KafkaListener for the particular group then only consumer is created inside this group
    // if we create 1 Kafka listerner then 1 consumer is creted
    // if we create more than 1 consumer under the same group then 2 consumer is created in same group
    //instead of creating seperate Kafka listener like above , we can just simply define in config level to define the consumer we need By
    // ⭐ factory.setConcurrency(3);

}
