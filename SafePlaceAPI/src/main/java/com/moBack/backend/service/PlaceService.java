package com.moBack.backend.service;

import java.util.List;
import java.util.Optional;

import com.moBack.backend.dto.NumberOfPeopleInPlaceDTO;
import com.moBack.backend.dto.PlaceDTO;
import com.moBack.backend.dto.PointDTO;
import com.moBack.backend.entity.Place;
import org.geolatte.geom.Point;

public interface PlaceService {
	
	public Optional<Place> findById(int id);

	public String save(PlaceDTO place);

	public boolean produceNumberOfPeople(NumberOfPeopleInPlaceDTO numberOfPeopleInPlaceDTO);
	
	public List<Place> findPlaces(PointDTO center, float radius);
	
	public void deleteById(int id);
}
