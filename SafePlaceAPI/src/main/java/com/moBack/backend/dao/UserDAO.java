package com.moBack.backend.dao;

import java.util.List;

import com.moBack.backend.entity.Position;
import com.moBack.backend.entity.User;
import com.moBack.backend.entity.UserPosition;

public interface UserDAO {
	
	public List<User> findAllUser();

	public User findById(int id);
	
	public void save(User user);
	
	public void updatePosition(int id, Position pos);
	
	public void deleteById(int id);

	public List<User> findUserFromPosition(Position center, double radius);

	
}
