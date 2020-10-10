package com.moBack.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moBack.backend.dao.StoreDAO;
import com.moBack.backend.entity.Position;
import com.moBack.backend.entity.Store;

@Service
public class StoreServiceImpl implements StoreService {
	
	private StoreDAO storeDAO;
	
	@Autowired
	public StoreServiceImpl(StoreDAO storeDAO) {
		this.storeDAO = storeDAO;
	}
	
	@Override
	@Transactional
	public List<Store> findAll() {
		return storeDAO.findAll();
	}

	@Override
	@Transactional
	public Store findById(int id) {
		return storeDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Store store) {
		storeDAO.save(store);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		storeDAO.deleteById(id);
	}

	@Override
	@Transactional
	public List<Store> findStoreFromPosition(Position center, double radius) {
		return storeDAO.findStoreFromPosition(center, radius);
	}
}
