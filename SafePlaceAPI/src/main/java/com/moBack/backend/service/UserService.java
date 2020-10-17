package com.moBack.backend.service;

import java.util.List;

import com.moBack.backend.dto.Position;
import com.moBack.backend.entity.User;

public interface UserService {
	
	public List<User> findAll();

	public User findById(int id);
	
	public List<User> findUserFromPosition(Position center, double radius);
	
	public User save(User user);
	
	public User updatePosition(int id, Position pos);
	
	public void deleteById(int id);

}
