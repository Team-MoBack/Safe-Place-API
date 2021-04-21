package com.moBack.backend.dto;

import com.moBack.backend.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.geolatte.geom.Point;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {

	private int id;
	private String name;
	private PointDTO location;
	private int numberOfPeople;

	public PlaceDTO(Place place) {
		id = place.getId();
		name = place.getName();
		Point p = place.getPosition();
		location = new PointDTO(p.getPosition().getCoordinate(0),p.getPosition().getCoordinate(1));
		numberOfPeople = place.getNumberOfPeople();
	}
}
