package com.moBack.backend.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.moBack.backend.dto.PlaceDTO;
import com.moBack.backend.dto.PointDTO;
import com.moBack.backend.entity.Place;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.moBack.backend.service.PlaceService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
	
	private PlaceService placeService;

	public PlaceController(PlaceService placeService) {
		this.placeService = placeService;
	}

	@ApiOperation(value = "위치와 반경을 넘기면 주변 장소 정보를 받아옵니다 ")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "radius", value = "반경(meter)", required = true, dataType = "double", paramType = "path")
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/search/{radius}")
	public List<PlaceDTO> findPlaces(@RequestBody PointDTO center, @PathVariable float radius) {
		return placeService.findPlaces(center,radius).stream().map(PlaceDTO::new).collect(Collectors.toList());
	}

	@ApiOperation(value = "place id에 해당하는 장 정보를 받아옵니다 ")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "가게 id", required = true, dataType = "int", paramType = "path")
	})
	@GetMapping("/{id}")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 접근"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	public Place getPlace(@PathVariable int id) {
		Optional<Place> place = placeService.findById(id);
		if (place.isPresent()) {
			return place.get();
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "place id not found - " + id);
	}
	
	@ApiOperation(value = "장소를 등록합니다 ")
	@PostMapping("/register")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	public Place register(@RequestBody Place place) {
		return placeService.save(place);
	}

}
