package com.moBack.backend.consumer;

import com.moBack.backend.config.KafkaConfig;
import com.moBack.backend.dao.PlaceRepository;
import com.moBack.backend.entity.PeopleInfo;
import com.moBack.backend.entity.Place;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PlaceInfoMessageListener {

    private PlaceRepository placeRepository;

    public PlaceInfoMessageListener(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @KafkaListener(topics = KafkaConfig.NUMBER_OF_PEOPLE_BY_PLACE_TOPIC, groupId = "PlaceInfoConsumer", containerFactory = "batchFactory")
    public void onMessage(List<ConsumerRecord<Integer, PeopleInfo>> records) {
        for (ConsumerRecord<Integer,PeopleInfo> record : records) {
            log.info("key : {}, value : {}",record.key(),record.value());
            Optional<Place> place = placeRepository.findById(record.key());
            if (place.isPresent()) {
                place.get().setNumberOfPeople(record.value().getNumberOfCurrentPeople());
                placeRepository.save(place.get());
            }
        }
    }
}
