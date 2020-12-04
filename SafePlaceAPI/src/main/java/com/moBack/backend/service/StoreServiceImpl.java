package com.moBack.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moBack.backend.dao.StoreRepository;
import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.entity.Store;

@Service
public class StoreServiceImpl implements StoreService {
	
	private StoreRepository storeRepository;
	
	@Autowired
	public StoreServiceImpl(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}
	
	@Override
	@Transactional
	public List<Store> findAll() {
		return storeRepository.findAll();
	}

	@Override
	@Transactional
	public Store findById(int id) {
		Optional<Store> result = storeRepository.findById(id);
		Store store = null;
		if (result.isPresent()) {
			store = result.get();
		}
		else {
			throw new RuntimeException("Did not find Store id - " + id);
		}
		return store;
	}

	@Override
	@Transactional
	public Store save(Store store) {
		return storeRepository.save(store);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		Optional<Store> storeOptional = storeRepository.findById(id);
		if (storeOptional.isPresent()) {
			storeRepository.delete(storeOptional.get());
		}
		else {
			throw new RuntimeException("did not find user id - " + id);
		}
	}

	@Override
	@Transactional
	public List<Store> findStoreFromPosition(PositionDTO center, double radius) {
		List<Store> stores = storeRepository.findAll();
		List<Store> filteredStores = new ArrayList<>();
		for (Store store : stores) {
			PositionDTO storePos = new PositionDTO(store.getLatitude(),store.getLongitude());
			if (center.distance(storePos,"M") < radius) {
				filteredStores.add(store);
			}
		}
		return filteredStores;
	}
}
