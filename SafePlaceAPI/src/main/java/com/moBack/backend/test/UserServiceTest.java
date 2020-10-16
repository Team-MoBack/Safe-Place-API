package com.moBack.backend.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.moBack.backend.BackendApplication;
import com.moBack.backend.entity.Store;
import com.moBack.backend.entity.User;
import com.moBack.backend.entity.UserPosition;
import com.moBack.backend.service.UserService;
import com.moBack.backend.util.Position;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {BackendApplication.class})
public class UserServiceTest {

	@Autowired private UserService userService;
	
	@Test
	public void findAllTest() {
		User user1 = new User("jaegu","kim","jaegu@gmail.com","1234");
		userService.save(user1);
		User user2 = new User("junhyun","park","junhyun@gmail.com","1234");
		userService.save(user2);
		List<User> users = userService.findAll();
		assertTrue(users.size()>2);
	}
	
	@Test
	public void findByIdTest() {
		User user1 = new User("jaegu","kim","jaegu@gmail.com","1234");
		userService.save(user1);
		assertNotNull(userService.findById(user1.getId()));
	}
	
	@Test
	public void saveTest() {
		User user1 = new User("jaegu","kim","jaegu@gmail.com","1234");
		userService.save(user1);
		assertNotNull(userService.findAll().size() > 1);
	}
	
	@Test
	public void deleteByIdTest() {
		User user1 = new User("jaegu","kim","jaegu@gmail.com","1234");
		userService.save(user1);
		userService.deleteById(user1.getId());
		assertNull(userService.findById(user1.getId()));
	}
	
	@Test
	public void findUserFromPositionTest() {
		User user1 = new User("jaegu","kim","jaegu@gmail.com","1234");
		userService.save(user1);
		user1.setUserPosition(new UserPosition(user1.getId(),35.818689,128.529462));
		userService.save(user1);
		
		User user2 = new User("junhyun","park","junhyun@gmail.com","1234");
		userService.save(user2);
		user2.setUserPosition(new UserPosition(user2.getId(),35.817925,128.533626));
		userService.save(user2);

		User user3 = new User("kildong","hong","kildong@gmail.com","1234");
		userService.save(user3);
		user3.setUserPosition(new UserPosition(user3.getId(),35.816207,128.530512));
		userService.save(user2);
		
		List<User> nearUsers = userService.findUserFromPosition(new Position(35.814346,128.524734), 1);
		assertTrue(nearUsers.size() >= 3);
	}
	
	

}