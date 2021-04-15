package com.moBack.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.moBack.backend.dao.PlaceRepository;
import com.moBack.backend.service.PlaceService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {BackendApplication.class})
public class PlaceServiceTest extends AbstractTest {

	@Autowired private PlaceService placeService;
	@MockBean private PlaceRepository placeRepository;

}