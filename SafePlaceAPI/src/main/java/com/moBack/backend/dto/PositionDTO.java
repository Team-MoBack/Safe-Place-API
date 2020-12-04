package com.moBack.backend.dto;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;

public class PositionDTO {

	double latitude;
	double longitude;

	public PositionDTO(){

	}

	public PositionDTO(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
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


	public double distance(PositionDTO pos, String unit) {
	    GeodeticCalculator geoCalc = new GeodeticCalculator();

	    Ellipsoid reference = Ellipsoid.WGS84;  

	    GlobalPosition userPos = new GlobalPosition(latitude, longitude, 0.0); // Point A

	    GlobalPosition targetPos = new GlobalPosition(pos.getLatitude(), pos.getLongitude(), 0.0); // Point B

	    double distance = geoCalc.calculateGeodeticCurve(reference, userPos, targetPos).getEllipsoidalDistance();
	    return unit.equals("M") ? distance : distance/1000;
	}

	@Override
	public String toString() {
		return "Position [longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
}
