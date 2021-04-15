package com.moBack.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.moBack.backend.dao.UserRepository;
import com.moBack.backend.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User findById(int id) {
		Optional<User> result = userRepository.findById(id);
		User user = null;
		if (result.isPresent()) {
			user = result.get();
		}
		else {
			throw new RuntimeException("Did not find user id - "+id);
		}
		return user;
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}
}
