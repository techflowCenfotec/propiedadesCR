package com.techflow.propiedadesCR.pojo;

public class PropertyPOJO {
	private int idProperty;
	private byte active;
	private String location;
	private String nerabyAreas;
	private double price;
	private double squareMetersConstruction;
	private ProvincePOJO province;
	
	public PropertyPOJO() {
		super();
	}
	public int getIdProperty() {
		return idProperty;
	}
	public void setIdProperty(int idProperty) {
		this.idProperty = idProperty;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte active) {
		this.active = active;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNerabyAreas() {
		return nerabyAreas;
	}
	public void setNerabyAreas(String nerabyAreas) {
		this.nerabyAreas = nerabyAreas;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSquareMetersConstruction() {
		return squareMetersConstruction;
	}
	public void setSquareMetersConstruction(double squareMetersConstruction) {
		this.squareMetersConstruction = squareMetersConstruction;
	}
	public ProvincePOJO getProvince() {
		return province;
	}
	public void setProvince(ProvincePOJO province) {
		this.province = province;
	}
	
	
}
