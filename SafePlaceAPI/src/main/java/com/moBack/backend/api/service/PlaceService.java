package com.moBack.backend.api.service;

import java.util.List;
import java.util.Optional;

import com.moBack.backend.api.entity.Place;
import com.moBack.backend.api.dto.NumberOfPeopleInPlaceDTO;
import com.moBack.backend.api.dto.PlaceDTO;
import com.moBack.backend.api.dto.PointDTO;

public interface PlaceService {
	
	public Optional<Place> findById(int id);

	public String save(PlaceDTO place);

	public boolean produceNumberOfPeople(NumberOfPeopleInPlaceDTO numberOfPeopleInPlaceDTO);
	
	public List<Place> findPlaces(PointDTO center, float radius);
	
	public void deleteById(int id);
}
