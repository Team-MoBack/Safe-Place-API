package com.moBack.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertTrue;

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
import com.moBack.backend.entity.User;
import com.moBack.backend.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {BackendApplication.class})
public class UserServiceTest extends AbstractTest {

	@Autowired
	private UserService userService;
	
	@MockBean(UserRepository.class) 
	private UserRepository userRepository;

	@Test(expected = RuntimeException.class)
	public void findByIdTest() {
		int userId = 1;
		Mockito.when(userRepository.findById(userId)).thenReturn(null);
		userService.findById(userId);
	}
}