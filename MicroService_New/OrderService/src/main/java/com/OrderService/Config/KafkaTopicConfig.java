package com.OrderService.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.producer.string}")
    private String MultibrokercustomerStringTopic;

    @Value("${kafka.topic.producer.object}")
    private String MultibrokercustomerObjectTopic;

    @Bean
    public NewTopic orderStringTopic() {
        return TopicBuilder.name(MultibrokercustomerStringTopic)
                .partitions(3)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic orderrObjectTopic() {
        return TopicBuilder.name(MultibrokercustomerObjectTopic)
                .partitions(3)
                .replicas(3)
                .build();
    }
}
