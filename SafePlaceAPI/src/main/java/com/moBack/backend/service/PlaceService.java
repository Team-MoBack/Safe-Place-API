package com.moBack.backend.service;

import java.util.List;

import com.moBack.backend.dto.PointDTO;
import com.moBack.backend.entity.Place;
import org.geolatte.geom.Point;

public interface PlaceService {
	
	public Place findById(int id);

	public Place save(Place place);
	
	public List<Place> findPlaces(PointDTO center, float radius);
	
	public void deleteById(int id);
}
