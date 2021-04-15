package com.moBack.backend;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.moBack.backend.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moBack.backend.entity.User;
import com.moBack.backend.service.UserService;

@Transactional
public class UserRestControllerTest extends AbstractTest {
	
	@MockBean
	private UserService userService;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void findAllTest() throws Exception {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		User user2 = new User("junhyun","park","junhyun@gmail.com","1234");
		Mockito.when(userService.findAll()).thenReturn(Arrays.asList(user1,user2));
	
		String uri = "/api/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		ObjectMapper mapper = new ObjectMapper();
		List<User> result = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<User>>() {});
		assertEquals(2,result.size());
	}

	@Test
	public void findByIdTest() throws Exception {
		User newUser = new User("jaegu","kim","jaegu88@gmail.com","1234");
		Mockito.when(userService.findById(newUser.getId())).thenReturn(newUser);
		String uri = String.format("/api/users/%d",newUser.getId());
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User user = super.mapFromJson(content, User.class);
		assertEquals(newUser.getEmail(),user.getEmail());
	}
	
	@Test
	public void saveUserTest() throws Exception {
		User newUser = new User("jaegu","kim","jaegu88@gmail.com","1234");
		UserDTO userDTO = new UserDTO(newUser.getFirstName(),newUser.getLastName(),newUser.getEmail(),newUser.getPassword());
		String json = super.mapToJson(userDTO);
		Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(newUser);
		String uri = "/api/users/register";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User savedUser  = super.mapFromJson(content, User.class);
		assertEquals(newUser.getEmail(),savedUser.getEmail());
	}

}