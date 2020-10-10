package com.moBack.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	String id;
	
	@Column(name="password")
	String password;
	
	@Column(name="name")
	String name;
	
	@Column(name="longitude")
	double longitude;
	
	@Column(name="latitude")
	double latitude;

	public User() {
		
	}
	
	public User(String id,String password,String name, double longitude, double latitude) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "User [id=" + id + ", name=" + name + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
	
	
}
