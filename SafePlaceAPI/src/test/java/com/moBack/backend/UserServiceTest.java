package com.moBack.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.moBack.backend.dao.UserRepository;
import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.entity.User;
import com.moBack.backend.entity.UserPosition;
import com.moBack.backend.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {BackendApplication.class})
public class UserServiceTest extends AbstractTest {

	@Autowired private UserService userService;
	
	@MockBean(UserRepository.class) 
	private UserRepository userRepository;
	
	@Test
	public void findAllTest() {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		User user2 = new User("junhyun","park","junhyun@gmail.com","1234");
		Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2));
		List<User> resultList = userService.findAll();
		assertTrue(resultList.size()>=2);
	}
	
	@Test
	public void findByIdTest() {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
		assertEquals(userService.findById(user1.getId()),user1);
	}
	
	@Test
	public void saveTest() {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		Mockito.when(userRepository.save(user1)).thenReturn(user1);
		User newUser = userService.save(user1);
		assertEquals(user1,newUser);
	}
	
	@Test
	public void deleteByIdTest() {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
		userService.deleteById(user1.getId());
		Mockito.when(userRepository.findById(user1.getId())).thenReturn(null);
		assertThrows(RuntimeException.class,() -> {userService.deleteById(user1.getId());});
	}
	
	@Test
	public void updateUserPositionTest() {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		
		Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
		PositionDTO updatedPos = new PositionDTO(35.818689,128.529462);
		User updatedUser = userService.updatePosition(user1.getId(), new PositionDTO(35.818689,128.529462));
		assertEquals(updatedPos.getLatitude(),updatedUser.getUserPosition().getLatitude(),0.001);
		assertEquals(updatedPos.getLongitude(),updatedUser.getUserPosition().getLongitude(),0.001);
		
		Mockito.when(userRepository.findById(user1.getId())).thenReturn(null);
		assertThrows(RuntimeException.class,() -> {userService.deleteById(user1.getId());});
	}
	
	@Test
	public void findUserFromPositionTest() {
		User user1 = new User("jaegu","kim","jaegu88@gmail.com","1234");
		user1.setUserPosition(new UserPosition(35.818689,128.529462)); // 0.64km
	
		User user2 = new User("junhyun","park","junhyun@gmail.com","1234"); // 0.8km
		user2.setUserPosition(new UserPosition(35.817925,128.533626));
		
		User user3 = new User("kildong","hong","kildong@gmail.com","1234"); //0.56km
		user3.setUserPosition(new UserPosition(35.816207,128.530512));
		
		Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2,user3));
		
		List<User> nearUsers = userService.findUserFromPosition(new PositionDTO(35.814346,128.524734), 1000);
		assertTrue(nearUsers.size() == 3);
	}
	
	

}