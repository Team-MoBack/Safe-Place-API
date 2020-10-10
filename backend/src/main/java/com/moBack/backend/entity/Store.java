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
	String storeName;
	
	@Column(name="owner_name")
	String ownerName;
	
	@Column(name="category")
	String category;
	
	@Column(name="latitude")
	double latitude;
	
	@Column(name="longitude")
	double longitude;

	public Store() {
		
	}
	
	public Store(String storeName, String ownerName, String category, double latitude, double longitude) {
		this.storeName = storeName;
		this.ownerName = ownerName;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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
		return "Store [storeName=" + storeName + ", ownerName=" + ownerName + ", category=" + category + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

}
