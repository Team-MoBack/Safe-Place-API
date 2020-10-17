package com.moBack.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_position")
public class UserPosition {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column(name = "longitude")
	double longitude;
	
	@Column(name = "latitude")
	double latitude;
	
	public UserPosition() {
		
	}
	
	public UserPosition(int id, double longitude, double latitude) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}



	public UserPosition(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "UserPosition [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
	
}
