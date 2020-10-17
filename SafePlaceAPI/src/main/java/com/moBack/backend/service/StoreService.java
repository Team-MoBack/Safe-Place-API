package com.moBack.backend.service;

import java.util.List;

import com.moBack.backend.dto.Position;
import com.moBack.backend.entity.Store;

public interface StoreService {
	
	public List<Store> findAll();

	public Store findById(int id);

	public Store save(Store store);
	
	public List<Store> findStoreFromPosition(Position center, double radius);
	
	public void deleteById(int id);
}
