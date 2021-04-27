package com.moBack.backend.api.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.transaction.Transactional;
import com.moBack.backend.api.config.KafkaConfig;
import com.moBack.backend.api.dto.NumberOfPeopleInPlaceDTO;
import com.moBack.backend.api.dto.PlaceDTO;
import com.moBack.backend.api.dto.PointDTO;
import com.moBack.backend.api.entity.Place;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.moBack.backend.api.dao.PlaceRepository;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {
	
	private PlaceRepository placeRepository;
	private KafkaTemplate<Integer, Integer> kafkaTemplate;
	private ListenableFuture listenableFuture;

	public PlaceServiceImpl(PlaceRepository placeRepository, KafkaTemplate kafkaTemplate) {
		this.placeRepository = placeRepository;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@Override
	@Transactional
	public Optional<Place> findById(int id) {
		return placeRepository.findById(id);
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
	@Transactional
	public boolean produceNumberOfPeople(NumberOfPeopleInPlaceDTO numberOfPeopleInPlaceDTO) {
		final ProducerRecord<Integer, Integer> record = new ProducerRecord<>(KafkaConfig.NUMBER_OF_PEOPLE_BY_PLACE_TOPIC,numberOfPeopleInPlaceDTO.getId(),numberOfPeopleInPlaceDTO.getNumberOfPeople());
		try {
			kafkaTemplate.send(record).get(2, TimeUnit.SECONDS);
			return true;
		}
		catch (ExecutionException | TimeoutException | InterruptedException e) {
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

	@Override
	public void setKafkaTemplate(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
}
