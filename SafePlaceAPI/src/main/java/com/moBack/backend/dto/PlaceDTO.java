package com.moBack.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDTO {
	
	private int id;
	
	private String name;

	private Point<G2D> location;

	public PlaceDTO(String name, Point<G2D> location) {
		this.name = name;
		this.location = location;
	} 
}
