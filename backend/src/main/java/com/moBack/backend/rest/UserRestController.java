package com.moBack.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.moBack.backend.entity.Position;
import com.moBack.backend.entity.User;
import com.moBack.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable int id) {
		User user = userService.findById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id not found - " + id);
		}
		return user;
	}
	
	@PostMapping("/login")
	public User authenticate(@RequestBody User user){
		List<User> users = userService.findAll();
		String email = user.getEmail();
		String pw = user.getPassword();
		for (User temp : users) {
			if (email.equals(temp.getEmail()) && pw.equals(temp.getPassword())) {
				return user;
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email or password is not matched");
	}
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		
		List<User> users = userService.findAll();
		for (User temp : users) {
			if (temp.getEmail().equals(user.getEmail())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id is duplicated - " + user.getId());
			}
		}
		userService.save(user);
		return user;
	}
	
	@PutMapping("/position/{id}")
	public Position updatePosition(@PathVariable int id, @RequestBody Position pos) {
		User user = userService.findById(id);
		if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user id is not found");
		userService.updatePosition(id, pos);
		return pos;
	}
	
	
}
