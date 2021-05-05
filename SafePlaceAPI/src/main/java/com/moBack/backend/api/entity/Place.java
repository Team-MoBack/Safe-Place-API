package com.moBack.backend.api.entity;

import com.moBack.backend.api.dto.PointDTO;
import lombok.*;
import org.geolatte.geom.Point;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(mappedBy="place")
	private List<Review> reviewList;

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

	public void addReview(Review review) {
		reviewList.add(review);
		review.setPlace(this);
	}

	public void deleteReview(Review review) {
		reviewList.remove(review);
		review.setPlace(null);
	}
}
