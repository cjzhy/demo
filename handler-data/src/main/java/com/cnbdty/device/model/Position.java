package com.cnbdty.device.model;
public class Position
{
	
	private double longitude;
	private double latitude;
	
	public Position()
	{
		super();
	}

	public Position(double longitude,double latitude)
	{
		super();
		this.longitude = longitude;
		this.latitude = latitude;
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
		StringBuilder builder = new StringBuilder();
		builder.append("Position [");
		builder.append("longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append("]");
		return builder.toString();
	}

	
	
}
