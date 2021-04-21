package com.moBack.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.moBack.backend.config.KafkaConfig;
import com.moBack.backend.dto.NumberOfPeopleInPlaceDTO;
import com.moBack.backend.dto.PointDTO;
import com.moBack.backend.entity.Place;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.moBack.backend.dao.PlaceRepository;
import org.springframework.util.concurrent.ListenableFuture;

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
	public Place save(Place place) {
		return placeRepository.save(place);
	}

	@Override
	public void produceNumberOfPeople(NumberOfPeopleInPlaceDTO numberOfPeopleInPlaceDTO) {
		final ProducerRecord<Integer, Integer> record = new ProducerRecord<>(KafkaConfig.NUMBER_OF_PEOPLE_BY_PLACE_TOPIC,numberOfPeopleInPlaceDTO.getId(),numberOfPeopleInPlaceDTO.getNumberOfPeople());
		ListenableFuture<SendResult<Integer,Integer>> future = kafkaTemplate.send(record);
		future.addCallback(new KafkaSendCallback<Integer,Integer>() {
			@Override
			public void onSuccess(SendResult<Integer, Integer> result) {
				ProducerRecord<Integer,Integer> res = result.getProducerRecord();
				log.info("success!");
				log.info(String.valueOf(res.key()));
				log.info(String.valueOf(res.value()));
			}

			@Override
			public void onFailure(KafkaProducerException ex) {
				log.info("error!");
			}
		});
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
