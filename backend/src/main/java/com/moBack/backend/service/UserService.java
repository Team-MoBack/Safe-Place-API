package com.moBack.backend.service;

import java.util.List;

import com.moBack.backend.entity.Position;
import com.moBack.backend.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User user);

	public void updatePosition(int id, Position pos);
	
	public void deleteById(int id);
}
