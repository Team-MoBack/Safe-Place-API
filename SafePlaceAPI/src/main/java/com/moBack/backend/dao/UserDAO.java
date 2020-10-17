package com.moBack.backend.dao;

import java.util.List;

import com.moBack.backend.dto.Position;
import com.moBack.backend.entity.User;

public interface UserDAO {
	
	public List<User> findAll();

	public User findById(int id);
	
	public void save(User user);
	
	public void updatePosition(int id, Position pos);
	
	public void deleteById(int id);

	public List<User> findUserFromPosition(Position center, double radius);

	
}
