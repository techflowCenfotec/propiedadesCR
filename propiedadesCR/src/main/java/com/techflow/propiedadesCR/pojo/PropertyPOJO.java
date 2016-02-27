package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tbenefit;
import com.techflow.propiedadesCR.ejb.TpropertyComment;
import com.techflow.propiedadesCR.ejb.TpropertyRating;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.ejb.Tprovince;
import com.techflow.propiedadesCR.ejb.Tresidence;
import com.techflow.propiedadesCR.ejb.Tuser;

public class PropertyPOJO {
	private int idProperty;
	private byte active;
	private String location;
	private String nearbyAreas;
	private double price;
	private String propertyImage;
	private double squareMeters;
	private List<Tbenefit> tbenefits;
	private TpropertyType tpropertyType;
	private Tprovince tprovince;
	private List<TpropertyComment> tpropertyComments;
	private List<TpropertyRating> tpropertyRatings;
	private List<Tresidence> tresidences;
	private List<Tuser> tusers;
	
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
	public String getNearbyAreas() {
		return nearbyAreas;
	}
	public void setNearbyAreas(String nearbyAreas) {
		this.nearbyAreas = nearbyAreas;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPropertyImage() {
		return propertyImage;
	}
	public void setPropertyImage(String propertyImage) {
		this.propertyImage = propertyImage;
	}
	public double getSquareMeters() {
		return squareMeters;
	}
	public void setSquareMeters(double squareMeters) {
		this.squareMeters = squareMeters;
	}
	public List<Tbenefit> getTbenefits() {
		return tbenefits;
	}
	public void setTbenefits(List<Tbenefit> tbenefits) {
		this.tbenefits = tbenefits;
	}
	public TpropertyType getTpropertyType() {
		return tpropertyType;
	}
	public void setTpropertyType(TpropertyType tpropertyType) {
		this.tpropertyType = tpropertyType;
	}
	public Tprovince getTprovince() {
		return tprovince;
	}
	public void setTprovince(Tprovince tprovince) {
		this.tprovince = tprovince;
	}
	public List<TpropertyComment> getTpropertyComments() {
		return tpropertyComments;
	}
	public void setTpropertyComments(List<TpropertyComment> tpropertyComments) {
		this.tpropertyComments = tpropertyComments;
	}
	public List<TpropertyRating> getTpropertyRatings() {
		return tpropertyRatings;
	}
	public void setTpropertyRatings(List<TpropertyRating> tpropertyRatings) {
		this.tpropertyRatings = tpropertyRatings;
	}
	public List<Tresidence> getTresidences() {
		return tresidences;
	}
	public void setTresidences(List<Tresidence> tresidences) {
		this.tresidences = tresidences;
	}
	public List<Tuser> getTusers() {
		return tusers;
	}
	public void setTusers(List<Tuser> tusers) {
		this.tusers = tusers;
	}
	
}
