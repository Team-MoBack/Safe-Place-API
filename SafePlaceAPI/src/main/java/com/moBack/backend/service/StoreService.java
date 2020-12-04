package com.moBack.backend.service;

import java.util.List;

import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.entity.Store;

public interface StoreService {
	
	public List<Store> findAll();

	public Store findById(int id);

	public Store save(Store store);
	
	public List<Store> findStoreFromPosition(PositionDTO center, double radius);
	
	public void deleteById(int id);
}
