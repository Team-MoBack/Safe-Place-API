package com.moBack.backend;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.moBack.backend.dao.UserRepository;
import com.moBack.backend.dto.Position;
import com.moBack.backend.entity.User;
import com.moBack.backend.entity.UserPosition;

@Transactional
public class UserRestControllerTest extends AbstractTest {
	
	@MockBean
	private UserRepository userRepository;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void findAllTest() throws Exception {
		String uri = "/api/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void findByIdTest() throws Exception {
		User newUser = userRepository.save(new User("jaegu","kim","jaegu88@gmail.com","1234"));
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
		String json = super.mapToJson(newUser);
		String uri = "/api/users/register";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User savedUser  = super.mapFromJson(content, User.class);
		System.out.println(savedUser);
		assertEquals(newUser.getEmail(),savedUser.getEmail());
	}

	@Test
	public void updatePositionTest() throws Exception {
		User user1 = userRepository.save(new User("jaegu","kim","jaegu88@gmail.com","1234"));
		Position newPos = new Position(35.818689,128.529462);
		String uri = String.format("/api/users/position/%d",user1.getId());
		String json = super.mapToJson(newPos);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		String content = mvcResult.getResponse().getContentAsString();
		UserPosition updatedPosition = super.mapFromJson(content, UserPosition.class);
		assertEquals(updatedPosition.getId(),user1.getId());
	}
	
	@Test
	public void findUserFromPositionTest() throws Exception {
		Position center = new Position(35.818689,128.529462);
		double radius = 1;
		String uri = String.format("/api/users/search/%f",radius);
		String json = super.mapToJson(center);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
	}
}