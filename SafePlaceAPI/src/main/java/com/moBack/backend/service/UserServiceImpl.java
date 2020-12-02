package com.moBack.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moBack.backend.dao.UserRepository;
import com.moBack.backend.dto.Position;
import com.moBack.backend.entity.User;
import com.moBack.backend.entity.UserPosition;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
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
	public List<User> findUserFromPosition(Position center, double radius){
		List<User> users = userRepository.findAll();
		List<User> filteredUsers = new ArrayList<>();
	
		for (User user : users) {
			System.out.println(user);
			UserPosition position = user.getUserPosition();
			Position userPos = new Position(position.getLatitude(),position.getLongitude());
			if (userPos.distance(center,"M") < radius) {
				filteredUsers.add(user);
			}
		}
		return filteredUsers;
	}

	@Override
	@Transactional
	public User save(User user) {
		user.setUserPosition(new UserPosition(0,0));
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User updatePosition(int id, Position pos) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setUserPosition(new UserPosition(pos.getLatitude(),pos.getLongitude()));
			userRepository.save(user);
		}
		else {
			throw new RuntimeException("did not find user id - " + id);
		}
		return userOptional.get();
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			userRepository.delete(userOptional.get());
		}
		else {
			throw new RuntimeException("did not find user id - " + id);
		}

	}


}
