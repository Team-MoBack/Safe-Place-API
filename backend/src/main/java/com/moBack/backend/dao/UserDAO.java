package com.moBack.backend.dao;

import java.util.List;

import com.moBack.backend.entity.User;

public interface UserDAO {
	
	public List<User> findAll();
}
