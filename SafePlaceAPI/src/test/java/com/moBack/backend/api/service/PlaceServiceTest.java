package com.moBack.backend.api.service;

import com.moBack.backend.api.AbstractTest;
import com.moBack.backend.api.dao.PlaceRepository;
import com.moBack.backend.api.dto.PeopleInfoDTO;
import com.moBack.backend.api.dto.PlaceDTO;
import com.moBack.backend.api.entity.Place;
import com.moBack.backend.api.dto.PointDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class PlaceServiceTest extends AbstractTest {

    @Autowired
    private PlaceService placeService;
    @MockBean
    private PlaceRepository placeRepository;
    @MockBean
    private KafkaTemplate<Integer, Integer> kafkaTemplate;
    @MockBean
    private ListenableFuture listenableFuture;

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
        placeService.setKafkaTemplate(kafkaTemplate);
    }

    @Test
    public void saveTest() {
        Assert.assertEquals("fail",placeService.save(dto1));
        Assert.assertEquals("success",placeService.save(dto2));
    }

    @Test
    public void produceNumberOfPeopleTest() throws InterruptedException, ExecutionException, TimeoutException {
        PeopleInfoDTO peopleInfoDTO = PeopleInfoDTO.builder()
                .id(1)
                .numberOfCurrentPeople(10)
                .numberOfNewPeople(2)
                .build();
        Mockito.when(kafkaTemplate.send(Mockito.any(ProducerRecord.class))).thenReturn(listenableFuture);
        Assert.assertTrue(placeService.producePeopleInfo(peopleInfoDTO));
        SettableListenableFuture<SendResult<String, Object>> future = new SettableListenableFuture<>();
        future.setException(new RuntimeException());
        Mockito.when(kafkaTemplate.send(Mockito.any(ProducerRecord.class))).thenReturn(future);
        Assert.assertFalse(placeService.producePeopleInfo(peopleInfoDTO));
    }
}
