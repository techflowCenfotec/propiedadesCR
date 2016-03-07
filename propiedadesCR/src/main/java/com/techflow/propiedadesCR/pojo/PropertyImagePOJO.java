package com.techflow.propiedadesCR.pojo;

public class PropertyImagePOJO {

	private int idPropertyImage;
	private String propertyImage;
	private PropertyPOJO tproperty;
	
	public PropertyImagePOJO() {
		super();
	}

	public int getIdPropertyImage() {
		return idPropertyImage;
	}

	public void setIdPropertyImage(int idPropertyImage) {
		this.idPropertyImage = idPropertyImage;
	}

	public String getPropertyImage() {
		return propertyImage;
	}

	public void setPropertyImage(String propertyImage) {
		this.propertyImage = propertyImage;
	}

	public PropertyPOJO getTproperty() {
		return tproperty;
	}

	public void setTproperty(PropertyPOJO tproperty) {
		this.tproperty = tproperty;
	}
}
