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
import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.entity.User;
import com.moBack.backend.entity.UserPosition;
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

	@Test
	public void updatePositionTest() throws Exception {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		UserPosition newPos = new UserPosition(35.818689,128.529462);
		user1.setUserPosition(newPos);
		Mockito.when(userService.findById(user1.getId())).thenReturn(user1);
		Mockito.when(userService.updatePosition(Mockito.anyInt(), Mockito.any(PositionDTO.class))).thenReturn(user1);
		String uri = String.format("/api/users/position/%d",user1.getId());
		String json = super.mapToJson(newPos);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		String content = mvcResult.getResponse().getContentAsString();
		UserPosition updatedPosition = super.mapFromJson(content, UserPosition.class);
		assertEquals(updatedPosition.getLatitude(),user1.getUserPosition().getLatitude(),0.001);
		assertEquals(updatedPosition.getLongitude(),user1.getUserPosition().getLongitude(),0.001);
		
		Mockito.when(userService.findById(user1.getId())).thenReturn(null);
		uri = String.format("/api/users/position/%d",user1.getId());
		json = super.mapToJson(newPos);
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(400,status);
	}
	
	@Test
	public void findUserFromPositionTest() throws Exception {
		
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		user1.setUserPosition(new UserPosition(35.818689,128.529462)); // 0.64km
	
		User user2 = new User("junhyun","park","junhyun@gmail.com","1234"); // 0.8km
		user2.setUserPosition(new UserPosition(35.817925,128.533626));
		
		User user3 = new User("kildong","hong","kildong@gmail.com","1234"); //0.56km
		user3.setUserPosition(new UserPosition(35.816207,128.530512));
		
		PositionDTO center = new PositionDTO(35.818689,128.529462);
		double radius = 1;
		Mockito.when(userService.findUserFromPosition(Mockito.any(PositionDTO.class), Mockito.anyDouble())).thenReturn(Arrays.asList(user1,user2,user3));
		String uri = String.format("/api/users/search/%f",radius);
		String json = super.mapToJson(center);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		
		ObjectMapper mapper = new ObjectMapper();
		List<User> result = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<User>>() {});
		assertEquals(3,result.size());
	}
}