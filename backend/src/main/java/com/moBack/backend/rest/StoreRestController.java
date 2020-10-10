package com.moBack.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.moBack.backend.entity.Position;
import com.moBack.backend.entity.Store;
import com.moBack.backend.service.StoreService;

@RestController
@RequestMapping("/api/stores")
public class StoreRestController {
	
	private StoreService storeService;

	@Autowired
	public StoreRestController(StoreService storeService) {
		this.storeService = storeService;
	}

	@GetMapping("")
	public List<Store> findAll(){
		return storeService.findAll();
	}
	
	@PostMapping("/search/{radius}")
	public List<Store> findStoresFromPosition(@PathVariable double radius, @RequestBody Position center){
		return storeService.findStoreFromPosition(center, radius);
	}


	@GetMapping("/{id}")
	public Store getStore(@PathVariable int id) {
		Store store = storeService.findById(id);
		if (store == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id not found - " + id);
		}
		return store;
	}

	@PostMapping("")
	public Store register(@RequestBody Store store) {

		storeService.save(store);
		return store;
	}

}
