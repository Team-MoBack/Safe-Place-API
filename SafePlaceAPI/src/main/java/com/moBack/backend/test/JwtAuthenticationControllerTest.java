package com.moBack.backend.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.moBack.backend.dto.JwtRequest;
import com.moBack.backend.entity.User;

@Transactional
public class JwtAuthenticationControllerTest extends AbstractTest {

	@Before
	@Override
	public void setUp() {
		super.setUp();
	}

	public void saveUser() throws Exception {
		User newUser = new User("jaegu","kim","jaegu88@gmail.com","1234");
		String json = super.mapToJson(newUser);
		String uri = "/api/users/register";
		mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
	}

	@Test
	public void loginTest() throws Exception {
		saveUser();
		JwtRequest request = new JwtRequest();
		String uri = "/api/login";
		request.setEmail("jaegu88@gmail.com");
		request.setPassword("1234");
		String json = super.mapToJson(request);
		System.out.println(json);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		assertTrue(content.contains("token"));
	}
}
