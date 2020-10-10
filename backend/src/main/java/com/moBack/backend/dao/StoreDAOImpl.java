package com.moBack.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moBack.backend.entity.Store;

@Repository
public class StoreDAOImpl implements StoreDAO{

	private EntityManager entityManager;
	
	@Autowired
	public StoreDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public List<Store> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		//Query<Store> theQuery = currentSession.createQuery("from ")
		
		return null;
	}

}
