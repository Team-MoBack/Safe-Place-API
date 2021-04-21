package com.moBack.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.transaction.Transactional;
import com.moBack.backend.config.KafkaConfig;
import com.moBack.backend.dto.NumberOfPeopleInPlaceDTO;
import com.moBack.backend.dto.PlaceDTO;
import com.moBack.backend.dto.PointDTO;
import com.moBack.backend.entity.Place;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.moBack.backend.dao.PlaceRepository;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {
	
	private PlaceRepository placeRepository;
	private KafkaTemplate<Integer, Integer> kafkaTemplate;

	public PlaceServiceImpl(PlaceRepository placeRepository, KafkaTemplate kafkaTemplate) {
		this.placeRepository = placeRepository;
		this.kafkaTemplate = kafkaTemplate;
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
	public String save(PlaceDTO place) {
		if (placeRepository.findById(place.getId()).isPresent()) {
			return "fail";
		}
		placeRepository.save(Place.createPlace(place.getName(),place.getLocation()));
		return "success";
	}

	@Override
	public boolean produceNumberOfPeople(NumberOfPeopleInPlaceDTO numberOfPeopleInPlaceDTO) {
		final ProducerRecord<Integer, Integer> record = new ProducerRecord<>(KafkaConfig.NUMBER_OF_PEOPLE_BY_PLACE_TOPIC,numberOfPeopleInPlaceDTO.getId(),numberOfPeopleInPlaceDTO.getNumberOfPeople());
		try {
			kafkaTemplate.send(record).get(2, TimeUnit.SECONDS);
			return true;
		}
		catch (ExecutionException e) {
			return false;
		}
		catch (TimeoutException | InterruptedException e) {
			return false;
		}
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
