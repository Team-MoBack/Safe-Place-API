package com.moBack.backend.api.dto;

import com.moBack.backend.api.entity.Place;
import lombok.*;
import org.geolatte.geom.Point;

@Getter
@Setter
@Builder
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
