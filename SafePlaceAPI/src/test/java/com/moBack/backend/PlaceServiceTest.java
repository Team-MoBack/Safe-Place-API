package com.moBack.backend;

import com.moBack.backend.dto.NumberOfPeopleInPlaceDTO;
import com.moBack.backend.service.PlaceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaceServiceTest extends AbstractTest{

    @Autowired
    private PlaceService placeService;

    @Test
    public void produceNumberOfPeopleTest() {
        placeService.produceNumberOfPeople(NumberOfPeopleInPlaceDTO.builder()
                .id(1)
                .numberOfPeople(10)
                .build()
        );
    }

}
