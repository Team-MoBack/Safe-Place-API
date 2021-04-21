package com.moBack.backend.entity;

import com.moBack.backend.dto.PointDTO;
import lombok.*;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Entity
@Table(name="place")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Place {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name="location")
	private Point position;

	@Column(name="number_of_people")
	private int numberOfPeople;

	public Place(String name, Point position) {
		this.name = name;
		this.position = position;
	}

	public static Place createPlace(String name, PointDTO position) {
		return Place.builder()
				.name(name)
				.position(point(WGS84,g(position.getLon(),position.getLat())))
				.build();
	}

}
