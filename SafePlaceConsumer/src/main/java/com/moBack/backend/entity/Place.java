package com.moBack.backend.entity;

import lombok.*;
import org.geolatte.geom.Point;

import javax.persistence.*;

@Entity
@Table(name="place")
@Getter
@Setter
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
}
