package com.moBack.backend.dao;

import java.util.List;

import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.entity.Store;

public interface StoreDAO {
	
	public List<Store> findAll();

	public Store findById(int id);

	public void save(Store store);

	public void deleteById(int id);

	public List<Store> findStoreFromPosition(PositionDTO center, double radius);
}
