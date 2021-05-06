package com.moBack.backend.api.service;

import java.util.List;
import java.util.Optional;

import com.moBack.backend.api.dto.ReviewDTO;
import com.moBack.backend.api.entity.Place;
import com.moBack.backend.api.dto.NumberOfPeopleInPlaceDTO;
import com.moBack.backend.api.dto.PlaceDTO;
import com.moBack.backend.api.dto.PointDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

public interface PlaceService {
	
	public Optional<Place> findById(int id);

	public String save(PlaceDTO place);

	public boolean produceNumberOfPeople(NumberOfPeopleInPlaceDTO numberOfPeopleInPlaceDTO);
	
	public List<Place> findPlaces(PointDTO center, float radius);
	
	public void deleteById(int id);

	public void setKafkaTemplate(KafkaTemplate kafkaTemplate);
}
