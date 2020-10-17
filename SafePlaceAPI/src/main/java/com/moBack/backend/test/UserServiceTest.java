package com.moBack.backend.test;

import static org.junit.Assert.assertEquals;
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
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		userService.save(user1);
		User user2 = new User("junhyun","park","junhyun@gmail.com","1234");
		userService.save(user2);
		List<User> users = userService.findAll();
		assertTrue(users.size()>=2);
	}
	
	@Test
	public void findByIdTest() {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		User savedUser = userService.save(user1);
		assertEquals(userService.findById(savedUser.getId()),savedUser);
	}
	
	@Test
	public void saveTest() {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		User newUser = userService.save(user1);
		assertEquals(user1,newUser);
	}
	
	@Test
	public void deleteByIdTest() {
		User user1 = userService.save(new User("jaegu","kim","jaegu88@gmail.com","1234"));
		System.out.println(user1.getId());
		userService.deleteById(user1.getId());
	}
	
	@Test
	public void updateUserPositionTest() {
		User user1 = userService.save(new User("jaegu","kim","jaegu88@gmail.com","1234"));
		user1.setUserPosition(new UserPosition(35.818689,128.529462));
		userService.save(user1);
		userService.updatePosition(user1.getId(), new Position(35.818689,128.529462));
	}
	
	@Test
	public void findUserFromPositionTest() {
		User user1 = userService.save(new User("jaegu","kim","jaegu88@gmail.com","1234"));
		user1.setUserPosition(new UserPosition(35.818689,128.529462));
		userService.save(user1);
		
		User user2 = userService.save(new User("junhyun","park","junhyun@gmail.com","1234"));
		user2.setUserPosition(new UserPosition(35.818689,128.529462));
		userService.save(user2);

		User user3 = userService.save(new User("kildong","hong","kildong@gmail.com","1234"));
		user3.setUserPosition(new UserPosition(35.818689,128.529462));
		userService.save(user3);
		
		List<User> nearUsers = userService.findUserFromPosition(new Position(35.814346,128.524734), 1);
		assertTrue(nearUsers.size() >= 3);
	}
	
	

}