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

import com.moBack.backend.dto.PositionDTO;
import com.moBack.backend.dto.StoreDTO;
import com.moBack.backend.entity.Store;
import com.moBack.backend.service.StoreService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
	
	private StoreService storeService;

	@Autowired
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	

	@ApiOperation(value = "전체 스토어 정보를 받아옵니다 ")
	@GetMapping("")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	public List<Store> findAll(){
		return storeService.findAll();
	}
	
	@ApiOperation(value = "유저위치와 반경을 넘기면 주변 가게정보를 받아옵니다 ")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "radius", value = "반경(meter)", required = true, dataType = "double", paramType = "path")
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/search/{radius}")
	public List<Store> findStoresFromPosition(@PathVariable double radius, @RequestBody PositionDTO center){
		return storeService.findStoreFromPosition(center, radius);
	}

	
	@ApiOperation(value = "가게id에 해당하는 가게정보를 받아옵니다 ")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "가게 id", required = true, dataType = "int", paramType = "path")
	})
	@GetMapping("/{id}")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 접근"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	public Store getStore(@PathVariable int id) {
		Store store = storeService.findById(id);
		if (store == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id not found - " + id);
		}
		return store;
	}
	
	@ApiOperation(value = "가게를 등록합니다 ")
	@PostMapping("/register")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	public Store register(@RequestBody StoreDTO store) {
		return storeService.save(Store.createStore(store.getName(), store.getOwner(), store.getCategory(), store.getLatitude(), store.getLongitude()));
	}

}
