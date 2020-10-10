package com.moBack.backend.dao;

import java.util.List;

import com.moBack.backend.entity.User;

public interface UserDAO {
	
	public List<User> findAll();
	
	public User findById(String id);
	
	public void save(User user);

	public void deleteById(int id);
}
