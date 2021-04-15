package com.moBack.backend;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.moBack.backend.entity.Place;
import org.geolatte.geom.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moBack.backend.service.PlaceService;

@Transactional
public class PlaceRestControllerTest extends AbstractTest {
	
	@MockBean
	private PlaceService placeService;
	
	@Override
	@Before
	public void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	
//	@Test
//	public void findAllTest() throws Exception {
//
//		Place place1 = new Place("star bucks sangin",new Point(35.123,128.123));
//		Place place2 = new Place("star bucks yongin",new Point(35.123,128.123));
//		Mockito.when(placeService.findAll()).thenReturn(Arrays.asList(place1, place2));
//
//		String uri = "/api/stores";
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//
//		ObjectMapper mapper = new ObjectMapper();
//		List<Place> list = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Place>>(){});
//		assertEquals(2,list.size());
//	}

//	@Test
//	public void findByIdTest() throws Exception {
//		Place place = new Place("star bucks sangin",new Point(35.123,128.123));
//		Mockito.when(placeService.findById(place.getId())).thenReturn(place);
//		String uri = String.format("/api/stores/%d", place.getId());
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		Place returnedPlace = super.mapFromJson(content, Place.class);
//		assertEquals(returnedPlace.getName(), place.getName());
//	}
//
//	@Test
//	public void saveStoreTest() throws Exception {
//		Place newPlace = new Place("star bucks sangin",new Point(35.123,128.123));
//		StoreDTO storeDTO = new StoreDTO(newPlace.getName(), newPlace.getName(), newPlace.getCategory(), newPlace.getLatitude(), newPlace.getLongitude());
//		Mockito.when(placeService.save(Mockito.any(Place.class))).thenReturn(newPlace);
//		String json = super.mapToJson(storeDTO);
//		String uri = "/api/stores/register";
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		Place savedPlace = super.mapFromJson(content, Place.class);
//		assertEquals(newPlace.getName(), savedPlace.getName());
//	}
//
//	@Test
//	public void findStoresFromPositionTest() throws Exception {
//		PositionDTO center = new PositionDTO(35.123,128.123);
//		double radius = 1000;
//		Place place1 = new Place("star bucks jincheon","Hong Kill Dong","Cafe",35.814346,128.524734);
//		Place place2 = new Place("star bucks yongin","Park Ji sung","Cafe",35.818496,128.536702);
//		Place place3 = new Place("angelius","amuge","Cafe",36.4344,154.12323);
//
//		Mockito.when(placeService.findStoreFromPosition(Mockito.any(PositionDTO.class), Mockito.anyDouble())).thenReturn(Arrays.asList(place1, place2, place3));
//
//		String uri = String.format("/api/stores/search/%f",radius);
//		String json = super.mapToJson(center);
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200,status);
//
//		ObjectMapper mapper = new ObjectMapper();
//		List<Place> result = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Place>>() {});
//		assertEquals(3,result.size());
//	}
//
}
