package com.Notification_Service.Config;

import com.Notification_Service.Model.Orderresponsemodel;
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
public class MultiNode_KafkaOrderconfig {

    @Value("${kafka.topic.order.string.group1}")
    private String ordergroup1;

    @Value("${kafka.topic.order.object.group2}")
    private String ordergroup2;

    // 1️⃣ Consumer for String messages
    @Bean
    public ConsumerFactory<String, String> stringOrderFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, ordergroup1);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerOrderFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringOrderFactory());
        return factory;
    }

    // 2️⃣ Consumer for Orderresponsemodel (JSON object)
    @Bean
    public ConsumerFactory<String, Orderresponsemodel> OrderConsumerFactory() {
        JsonDeserializer<Orderresponsemodel> deserializer = new JsonDeserializer<>(Orderresponsemodel.class);
        deserializer.addTrustedPackages("*");
        deserializer.setRemoveTypeHeaders(false);
        deserializer.setUseTypeMapperForKey(false);
        deserializer.setUseTypeHeaders(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, ordergroup2);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.Notification_Service.Model.Customermodel");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Orderresponsemodel> OrderKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Orderresponsemodel> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(OrderConsumerFactory());
        factory.setConcurrency(3);
        return factory;
    }
}
