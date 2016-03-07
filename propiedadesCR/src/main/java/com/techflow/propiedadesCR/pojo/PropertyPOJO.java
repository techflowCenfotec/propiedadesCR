package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.ejb.Tprovince;

public class PropertyPOJO {
	
	private int idProperty;
	private byte active;
	private String location;
	private String nearbyAreas;
	private double price;
	private String propertyImage;
	private double squareMeters;
	private List<BenefitsPOJO> tbenefits;
	private TpropertyType tpropertyType;
	private Tprovince tprovince;
	private List<CommentsPOJO> tpropertyComments;
	private List<RatingPOJO> tpropertyRatings;
	private List<UserPOJO> tusers;
	
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

	public List<BenefitsPOJO> getTbenefits() {
		return tbenefits;
	}

	public void setTbenefits(List<BenefitsPOJO> tbenefits) {
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

	public List<CommentsPOJO> getTpropertyComments() {
		return tpropertyComments;
	}

	public void setTpropertyComments(List<CommentsPOJO> tpropertyComments) {
		this.tpropertyComments = tpropertyComments;
	}

	public List<RatingPOJO> getTpropertyRatings() {
		return tpropertyRatings;
	}

	public void setTpropertyRatings(List<RatingPOJO> tpropertyRatings) {
		this.tpropertyRatings = tpropertyRatings;
	}

	public List<UserPOJO> getTusers() {
		return tusers;
	}

	public void setTusers(List<UserPOJO> tusers) {
		this.tusers = tusers;
	}
}
