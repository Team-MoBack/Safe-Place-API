package com.moBack.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moBack.backend.entity.User;
import com.moBack.backend.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable String id) {
		User user = userService.findById(id);
		if (user == null) {
			throw new RuntimeException("user id not found - " + id);
		}
		return user;
	}
	
	
	
}
