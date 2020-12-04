package com.moBack.backend.dto;


public class StoreDTO {
	
	private int id;
	
	private String name;

	private String owner;
	
	private String category;
	
	private double latitude;
	
	private double longitude;
	
	public StoreDTO() {
		
	}
	
	public StoreDTO(String name, String ownerName, String category, double latitude, double longitude) {
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
		return "StoreDTO [id=" + id + ", name=" + name + ", owner=" + owner + ", category=" + category + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	
	

}
