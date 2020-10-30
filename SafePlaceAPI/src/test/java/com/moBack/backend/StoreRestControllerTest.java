package com.moBack.backend;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.moBack.backend.dao.StoreRepository;
import com.moBack.backend.dto.Position;
import com.moBack.backend.entity.Store;

@Transactional
public class StoreRestControllerTest extends AbstractTest {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Override
	@Before
	public void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	
	@Test
	public void findAllTest() throws Exception {
		String uri = "/api/stores";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void findByIdTest() throws Exception {
		Store newStore = storeRepository.save(new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123));
		String uri = String.format("/api/stores/%d",newStore.getId());
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Store store = super.mapFromJson(content, Store.class);
		assertEquals(newStore.getName(),store.getName());
	}
	
	@Test
	public void saveStoreTest() throws Exception {
		Store newStore = new Store("star bucks sangin","Hong Kill Dong","Cafe",35.123,128.123);
		String json = super.mapToJson(newStore);
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
		Position center = new Position(35.123,128.123);
		double radius = 1;
		String uri = String.format("/api/stores/search/%f",radius);
		String json = super.mapToJson(center);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
	}
	
}
