package com.moBack.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.moBack.backend.dto.JwtRequest;
import com.moBack.backend.entity.User;
import com.moBack.backend.service.JwtUserDetailsService;

@Transactional
public class JwtAuthenticationControllerTest extends AbstractTest {

	@MockBean
    private JwtUserDetailsService userDetailService;
    
	@Before
	@Override
	public void setUp() {
		super.setUp();
	}

	@Test
	public void loginTest() throws Exception {
		JwtRequest request = new JwtRequest();
		User mockUser = new User("jaegu","kim","jaegu88@gmail.com","1234");
		String uri = "/api/login";
		request.setEmail(mockUser.getEmail());
		request.setPassword(mockUser.getPassword());
		Mockito.when(userDetailService.authenticateByEmailAndPassword(request.getEmail(), request.getPassword())).thenReturn(mockUser);
		String json = super.mapToJson(request);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		assertTrue(content.contains("token"));
	}
}
