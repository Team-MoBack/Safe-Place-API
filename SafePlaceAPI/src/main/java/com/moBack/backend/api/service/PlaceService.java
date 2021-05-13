package com.moBack.backend.api.service;

import java.util.List;
import java.util.Optional;

import com.moBack.backend.api.entity.Place;
import com.moBack.backend.api.dto.PeopleInfoDTO;
import com.moBack.backend.api.dto.PlaceDTO;
import com.moBack.backend.api.dto.PointDTO;
import org.springframework.kafka.core.KafkaTemplate;

public interface PlaceService {
	
	public Optional<Place> findById(int id);

	public String save(PlaceDTO place);

	public boolean producePeopleInfo(PeopleInfoDTO poepleInfoDTO);
	
	public List<Place> findPlaces(PointDTO center, float radius);
	
	public void deleteById(int id);

	public void setKafkaTemplate(KafkaTemplate kafkaTemplate);
}
