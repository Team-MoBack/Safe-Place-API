package com.moBack.backend.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moBack.backend.entity.Position;
import com.moBack.backend.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	private EntityManager entityManager;
	
	@Autowired
	public UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<User> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> theQuery = currentSession.createQuery("from User",User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	public User findById(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		User user = currentSession.get(User.class, id);
		
		return user;
	}
	
	@Override
	public List<User> findUserFromPosition(Position center,double radius){
		List<User> users = findAll();
		List<User> filteredUsers = new ArrayList<>();
		for (User user : users) {
			Position userPos = new Position(user.getLongitude(),user.getLatitude());
			if (userPos.distance(center,"K")/1000 < radius) {
				filteredUsers.add(user);
			}
		}
		return filteredUsers;
	}
	
	@Override
	public void save(User user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);
	}
	
	@Override
	public void updatePosition(int id, Position pos) {
		Session currentSession = entityManager.unwrap(Session.class);
		User user = findById(id);
		user.setLongitude(pos.getLongitude());
		user.setLatitude(pos.getLatitude());
		currentSession.saveOrUpdate(user);
		
	}


	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

	}
}