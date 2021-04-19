package com.moBack.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.moBack.backend.dto.PointDTO;
import com.moBack.backend.entity.Place;
import org.springframework.stereotype.Service;

import com.moBack.backend.dao.PlaceRepository;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	private PlaceRepository placeRepository;
	
	public PlaceServiceImpl(PlaceRepository placeRepository) {
		this.placeRepository = placeRepository;
	}
	
	@Override
	@Transactional
	public Optional<Place> findById(int id) {
		Optional<Place> result = placeRepository.findById(id);
		Place place = null;
		if (result.isPresent()) {
			place = result.get();
		}
		return Optional.of(place);
	}

	@Override
	@Transactional
	public Place save(Place place) {
		return placeRepository.save(place);
	}

	@Override
	@Transactional
	public List<Place> findPlaces(PointDTO center, float radius) {
		return placeRepository.getPlaces(center,radius);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		placeRepository.deleteById(id);
	}
}
