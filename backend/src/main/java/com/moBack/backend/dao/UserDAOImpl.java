package com.moBack.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public User findById(String id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		User user = currentSession.get(User.class, id);
		
		return user;
	}

	@Override
	public void save(User user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

	}

}
