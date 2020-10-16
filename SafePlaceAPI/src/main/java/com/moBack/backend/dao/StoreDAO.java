package com.moBack.backend.dao;

import java.util.List;

import com.moBack.backend.entity.Store;
import com.moBack.backend.util.Position;

public interface StoreDAO {
	
	public List<Store> findAll();

	public Store findById(int id);

	public Store save(Store store);

	public void deleteById(int id);

	public List<Store> findStoreFromPosition(Position center, double radius);
}
