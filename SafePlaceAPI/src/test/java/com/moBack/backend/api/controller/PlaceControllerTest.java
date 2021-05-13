package com.moBack.backend.api.controller;

import com.moBack.backend.api.AbstractTest;
import com.moBack.backend.api.dto.PeopleInfoDTO;
import com.moBack.backend.api.entity.Place;
import com.moBack.backend.api.rest.PlaceController;
import com.moBack.backend.api.service.PlaceService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class PlaceControllerTest extends AbstractTest {

    @MockBean
    private PlaceService placeService;
    @Autowired
    private PlaceController controller;

    @Test(expected=ResponseStatusException.class)
    public void getPlaceTest() {
        int id = 1;
        Mockito.when(placeService.findById(id)).thenReturn(Optional.of(new Place()));
        Assert.assertNotNull(controller.getPlace(id));
        Mockito.when(placeService.findById(id)).thenReturn(Optional.empty());
        controller.getPlace(id);
    }

    @Test
    public void successfulProduceNumberOfPeopleTest() {
        PeopleInfoDTO dto = new PeopleInfoDTO(10,10,2);
        Mockito.when(placeService.producePeopleInfo(dto)).thenReturn(true);
        controller.produceNumberOfPeople(dto);
    }

    @Test(expected=ResponseStatusException.class)
    public void failedProduceNumberOfPeopleTest() {
        PeopleInfoDTO dto = new PeopleInfoDTO(10,10,2);
        Mockito.when(placeService.producePeopleInfo(dto)).thenReturn(false);
        controller.produceNumberOfPeople(dto);
    }
}
