package com.moBack.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moBack.backend.dao.UserDAO;
import com.moBack.backend.entity.User;
import com.moBack.backend.util.Position;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	@Transactional
	public User findById(int id) {
		return userDAO.findById(id);
	}

	@Override
	@Transactional
	public List<User> findUserFromPosition(Position center, double radius){
		return userDAO.findUserFromPosition(center, radius);
	}

	@Override
	@Transactional
	public void save(User user) {
		System.out.println(user);
		userDAO.save(user);
	}

	@Override
	@Transactional
	public void updatePosition(int id, Position pos) {
		userDAO.updatePosition(id, pos);	
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		userDAO.deleteById(id);
	}


}
