package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.TpropertyComment;
import com.techflow.propiedadesCR.ejb.TpropertyRating;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.ejb.Tuser;

public class PropertyPOJO {
	
	private int idProperty;
	private byte active;
	private String address;
	private String coordinates;
	private double price;
	private double squareMeters;
	private List<BenefitsPOJO> tbenefits;
	private DistrictPOJO tdistrict;
	private TpropertyType tpropertyType;
	private Tuser tuser;
	private List<TpropertyComment> tpropertyComments; //Change to POJO
	private List<TpropertyRating> tpropertyRatings; //Change to POJO
	private List<UserPOJO> tusers;
	private List<PropertyImagePOJO> tpropertyImages;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public DistrictPOJO getTdistrict() {
		return tdistrict;
	}

	public void setTdistrict(DistrictPOJO tdistrict) {
		this.tdistrict = tdistrict;
	}

	public TpropertyType getTpropertyType() {
		return tpropertyType;
	}

	public void setTpropertyType(TpropertyType tpropertyType) {
		this.tpropertyType = tpropertyType;
	}

	public Tuser getTuser() {
		return tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
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

	public List<UserPOJO> getTusers() {
		return tusers;
	}

	public void setTusers(List<UserPOJO> tusers) {
		this.tusers = tusers;
	}

	public List<PropertyImagePOJO> getTpropertyImages() {
		return tpropertyImages;
	}

	public void setTpropertyImages(List<PropertyImagePOJO> tpropertyImages) {
		this.tpropertyImages = tpropertyImages;
	}
	
}
