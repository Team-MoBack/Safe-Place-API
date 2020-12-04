package com.moBack.backend;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

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
import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.dto.StoreDTO;
import com.moBack.backend.entity.Store;
import com.moBack.backend.service.StoreService;

@Transactional
public class StoreRestControllerTest extends AbstractTest {
	
	@MockBean
	private StoreService storeService;
	
	@Override
	@Before
	public void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	
	@Test
	public void findAllTest() throws Exception {
		
		Store store1 = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		Store store2 = new Store("star bucks yongin","Park Ji sung","Cafe",35.123,128.123);
		Mockito.when(storeService.findAll()).thenReturn(Arrays.asList(store1,store2));
	
		String uri = "/api/stores";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		ObjectMapper mapper = new ObjectMapper();
		List<Store> list = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Store>>(){});
		assertEquals(2,list.size());
	}

	@Test
	public void findByIdTest() throws Exception {
		Store store = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		Mockito.when(storeService.findById(store.getId())).thenReturn(store);
		String uri = String.format("/api/stores/%d",store.getId());
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Store returnedStore = super.mapFromJson(content, Store.class);
		assertEquals(returnedStore.getName(),store.getName());
	}
	
	@Test
	public void saveStoreTest() throws Exception {
		Store newStore = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		StoreDTO storeDTO = new StoreDTO(newStore.getName(),newStore.getName(),newStore.getCategory(),newStore.getLatitude(),newStore.getLongitude());
		Mockito.when(storeService.save(Mockito.any(Store.class))).thenReturn(newStore);
		String json = super.mapToJson(storeDTO);
		String uri = "/api/stores/register";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Store savedStore  = super.mapFromJson(content, Store.class);
		assertEquals(newStore.getName(),savedStore.getName());
	}
	
	@Test
	public void findStoresFromPositionTest() throws Exception {
		PositionDTO center = new PositionDTO(35.123,128.123);
		double radius = 1000;
		Store store1 = new Store("star bucks jincheon","Hong Kill Dong","Cafe",35.814346,128.524734);
		Store store2 = new Store("star bucks yongin","Park Ji sung","Cafe",35.818496,128.536702);
		Store store3 = new Store("angelius","amuge","Cafe",36.4344,154.12323);
	
		Mockito.when(storeService.findStoreFromPosition(Mockito.any(PositionDTO.class), Mockito.anyDouble())).thenReturn(Arrays.asList(store1,store2,store3));
		
		String uri = String.format("/api/stores/search/%f",radius);
		String json = super.mapToJson(center);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		
		ObjectMapper mapper = new ObjectMapper();
		List<Store> result = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Store>>() {});
		assertEquals(3,result.size());
	}
	
}
