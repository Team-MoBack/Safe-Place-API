package com.moBack.backend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String NUMBER_OF_PEOPLE_BY_PLACE_TOPIC = "number-of-people-by-place";

    @Bean
    public NewTopic numberOfPeopleByPlace() {
        return TopicBuilder.name(NUMBER_OF_PEOPLE_BY_PLACE_TOPIC)
                .partitions(10)
                .replicas(1)
                .compact()
                .build();
    }
}
