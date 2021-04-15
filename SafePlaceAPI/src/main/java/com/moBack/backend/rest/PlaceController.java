package com.moBack.backend.rest;

import java.util.List;

import com.moBack.backend.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.moBack.backend.dto.StoreDTO;
import com.moBack.backend.service.PlaceService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/stores")
public class PlaceController {
	
	private PlaceService placeService;

	@Autowired
	public PlaceController(PlaceService placeService) {
		this.placeService = placeService;
	}
	

	@ApiOperation(value = "전체 스토어 정보를 받아옵니다 ")
	@GetMapping("")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	public List<Place> findAll(){
		return placeService.findAll();
	}
	
//	@ApiOperation(value = "유저위치와 반경을 넘기면 주변 가게정보를 받아옵니다 ")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "radius", value = "반경(meter)", required = true, dataType = "double", paramType = "path")
//	})
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공"),
//		@ApiResponse(code = 500, message = "서버 에러")
//	})
//	@PostMapping("/search/{radius}")
//	public List<Place> findStoresFromPosition(@PathVariable double radius, @RequestBody PositionDTO center){
//		return placeService.findStoreFromPosition(center, radius);
//	}

	
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
	public Place getStore(@PathVariable int id) {
		Place place = placeService.findById(id);
		if (place == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id not found - " + id);
		}
		return place;
	}
	
//	@ApiOperation(value = "가게를 등록합니다 ")
//	@PostMapping("/register")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공"),
//		@ApiResponse(code = 500, message = "서버 에러")
//	})
//	public Place register(@RequestBody StoreDTO store) {
//		return placeService.save(Place.createStore(store.getName(), store.getOwner(), store.getCategory(), store.getLatitude(), store.getLongitude()));
//	}

}