package com.moBack.backend.service;

import com.moBack.backend.AbstractTest;
import com.moBack.backend.config.KafkaConfig;
import com.moBack.backend.dao.PlaceRepository;
import com.moBack.backend.dto.NumberOfPeopleInPlaceDTO;
import com.moBack.backend.dto.PlaceDTO;
import com.moBack.backend.dto.PointDTO;
import com.moBack.backend.entity.Place;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class PlaceServiceTest extends AbstractTest {

    @Autowired
    private PlaceService placeService;
    @MockBean
    private PlaceRepository placeRepository;
    @MockBean
    private KafkaTemplate<Integer, Integer> kafkaTemplate;
    private int id1 = 1;
    private int id2 = 2;
    private PlaceDTO dto1 = PlaceDTO.builder()
            .id(id1)
            .location(new PointDTO(37.125,37.125))
            .build();
    private PlaceDTO dto2 = PlaceDTO.builder()
            .id(id2)
            .location(new PointDTO(37.125,37.125))
            .build();

    @Before
    public void setup() {
        Mockito.when(placeRepository.findById(id1)).thenReturn(Optional.of(new Place()));
        Mockito.when(placeRepository.findById(id2)).thenReturn(Optional.empty());
    }


    @Test
    public void findByIdTest() {
        Assert.assertEquals(Optional.empty(),placeService.findById(id1));
        Assert.assertTrue(placeService.findById(id2).isPresent());
    }

    @Test
    public void saveTest() {
        Assert.assertEquals("fail",placeService.save(dto1));
        Assert.assertEquals("success",placeService.save(dto2));
    }

    @Test
    public void produceNumberOfPeopleTest() {
        ProducerRecord<Integer, Integer> record = new ProducerRecord<>(KafkaConfig.NUMBER_OF_PEOPLE_BY_PLACE_TOPIC,1,1);
        NumberOfPeopleInPlaceDTO numberOfPeopleInPlaceDTO = NumberOfPeopleInPlaceDTO.builder()
                .id(1)
                .numberOfPeople(10)
                .build();
        Mockito.when(kafkaTemplate.send(record)).thenReturn(null);
        Assert.assertFalse(placeService.produceNumberOfPeople(numberOfPeopleInPlaceDTO));
        Mockito.when(kafkaTemplate.send(record)).thenThrow(ExecutionException.class);
        Assert.assertFalse(placeService.produceNumberOfPeople(numberOfPeopleInPlaceDTO));
        Mockito.when(kafkaTemplate.send(record)).thenThrow(ExecutionException.class);
        Assert.assertFalse(placeService.produceNumberOfPeople(numberOfPeopleInPlaceDTO));
        Mockito.when(kafkaTemplate.send(record)).thenThrow(ExecutionException.class);
        Assert.assertFalse(placeService.produceNumberOfPeople(numberOfPeopleInPlaceDTO));
    }
}
