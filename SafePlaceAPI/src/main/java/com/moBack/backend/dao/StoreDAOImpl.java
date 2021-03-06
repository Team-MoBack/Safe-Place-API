package com.moBack.backend.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.entity.Store;

@Repository
public class StoreDAOImpl implements StoreDAO{

	private EntityManager entityManager;
	
	@Autowired
	public StoreDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Store> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Store> theQuery = currentSession.createQuery("from Store",Store.class);
		List<Store> stores = theQuery.getResultList();
		return stores;
	}
	
	@Override
	public List<Store> findStoreFromPosition(PositionDTO center, double radius){
		System.out.println(center);
		List<Store> stores = findAll();
		List<Store> filteredStores = new ArrayList<>();
		for (Store store : stores) {
			PositionDTO storePos = new PositionDTO(store.getLongitude(),store.getLatitude());
			System.out.println(center.distance(storePos,"K")*1000);
			if (center.distance(storePos,"K")/1000 < radius) {
				filteredStores.add(store);
			}
		}
		return filteredStores;
	}
	
	@Override
	public Store findById(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Store store = currentSession.get(Store.class, id);
		return store;
	}
	
	@Override
	public void save(Store store) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(store);
	}
	
	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Store store = currentSession.load(Store.class, id);
		if (store != null)
			currentSession.delete(store);
	}
	
}
