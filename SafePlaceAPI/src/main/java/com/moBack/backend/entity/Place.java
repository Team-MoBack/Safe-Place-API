package com.moBack.backend.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.geolatte.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="place")
@Getter
@Setter
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

	public Place(String name, Point position) {
		this.name = name;
		this.position = position;
	}

	public static Place createPlace(String name, Point position) {
		return new Place(name, position);
	}

}
