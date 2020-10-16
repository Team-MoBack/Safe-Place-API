package com.moBack.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="store")
public class Store {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="name")
	String name;
	
	@Column(name="owner")
	String owner;
	
	@Column(name="category")
	String category;
	
	@Column(name="latitude")
	double latitude;
	
	@Column(name="longitude")
	double longitude;

	public Store() {
		
	}
	
	public Store(String name, String ownerName, String category, double latitude, double longitude) {
		this.name = name;
		this.owner = ownerName;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", ownerName=" + owner + ", category=" + category
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
