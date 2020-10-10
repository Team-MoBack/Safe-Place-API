package com.moBack.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moBack.backend.dao.UserDAO;
import com.moBack.backend.entity.User;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	private UserDAO userDAO;
	
	@Autowired
	public UserRestController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@GetMapping("/users")
	public List<User> findAll(){
		return userDAO.findAll();
	}
}
