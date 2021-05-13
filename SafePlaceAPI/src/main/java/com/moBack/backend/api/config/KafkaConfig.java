package com.moBack.backend.api.config;

import com.moBack.backend.api.dto.PeopleInfoDTO;
import com.moBack.backend.api.entity.PeopleInfo;
import com.moBack.backend.api.serializer.PeopleInfoSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    public static final String NUMBER_OF_PEOPLE_BY_PLACE_TOPIC = "people-info-by-place";

    @Bean
    public NewTopic numberOfPeopleByPlace() {
        return TopicBuilder.name(NUMBER_OF_PEOPLE_BY_PLACE_TOPIC)
                .partitions(10)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public ProducerFactory<Integer, PeopleInfo> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String,Object> producerConfigs() {
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PeopleInfoSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<Integer,PeopleInfo> kafkaTemplate() {
        return new KafkaTemplate<Integer,PeopleInfo>(producerFactory());
    }
}
