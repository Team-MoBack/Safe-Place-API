package com.moBack.backend.service;

import java.util.List;

import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.entity.User;

public interface UserService {
	
	public List<User> findAll();

	public User findById(int id);
	
	public List<User> findUserFromPosition(PositionDTO center, double radius);
	
	public User save(User user);
	
	public User updatePosition(int id, PositionDTO pos);
	
	public void deleteById(int id);

}
