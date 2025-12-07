package com.CustomerService.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.customer.string}")
    private String SinglrbrokercustomerStringTopic;

    @Value("${kafka.topic.customer.object}")
    private String SinglrbrokercustomerObjectTopic;

    @Bean
    public NewTopic customerStringTopic() {
        return TopicBuilder.name(SinglrbrokercustomerStringTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic customerObjectTopic() {
        return TopicBuilder.name(SinglrbrokercustomerObjectTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
