package com.moBack.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moBack.backend.entity.Store;

public interface StoreRepository  extends JpaRepository<Store, Integer>{
	
}
