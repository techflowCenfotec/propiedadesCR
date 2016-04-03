package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Date;


/**
 * The persistent class for the tproperties database table.
 * 
 */
@Entity
@Table(name="tproperties")
@NamedQuery(name="Tproperty.findAll", query="SELECT t FROM Tproperty t")
public class Tproperty implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idProperty;
	private byte active;
	private String address;
	private String coordinates;
	private double price;
	private double squareMeters;
	private List<Tbenefit> tbenefits;
	private Tdistrict tdistrict;
	private TpropertyType tpropertyType;
	private Tuser tuser;
	private List<TpropertyComment> tpropertyComments;
	private List<TpropertyRating> tpropertyRatings;
	private List<Tuser> tusers;
	private List<TpropertyImage> tpropertyImages;
	private double offerPecentage;
	private byte isSold;
	private Date soldDate;
	private String saleType;
	private int totalViews;
	

	public Tproperty() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_property")
	public int getIdProperty() {
		return this.idProperty;
	}

	public void setIdProperty(int idProperty) {
		this.idProperty = idProperty;
	}


	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}


	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	@Column(name="square_meters")
	public double getSquareMeters() {
		return this.squareMeters;
	}

	public void setSquareMeters(double squareMeters) {
		this.squareMeters = squareMeters;
	}


	//bi-directional many-to-many association to Tbenefit
	@ManyToMany
	@JoinTable(
		name="tbenefit_per_property"
		, joinColumns={
			@JoinColumn(name="id_property")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_benefit")
			}
		)
	@JsonIgnore
	public List<Tbenefit> getTbenefits() {
		return this.tbenefits;
	}

	public void setTbenefits(List<Tbenefit> tbenefits) {
		this.tbenefits = tbenefits;
	}


	//bi-directional many-to-one association to Tdistrict
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_district")
	public Tdistrict getTdistrict() {
		return this.tdistrict;
	}

	public void setTdistrict(Tdistrict tdistrict) {
		this.tdistrict = tdistrict;
	}


	//bi-directional many-to-one association to TpropertyType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_property_type")
	public TpropertyType getTpropertyType() {
		return this.tpropertyType;
	}

	public void setTpropertyType(TpropertyType tpropertyType) {
		this.tpropertyType = tpropertyType;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_vendor")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}


	//bi-directional many-to-one association to TpropertyComment
	@OneToMany(mappedBy="tproperty")
	public List<TpropertyComment> getTpropertyComments() {
		return this.tpropertyComments;
	}

	public void setTpropertyComments(List<TpropertyComment> tpropertyComments) {
		this.tpropertyComments = tpropertyComments;
	}

	public TpropertyComment addTpropertyComment(TpropertyComment tpropertyComment) {
		getTpropertyComments().add(tpropertyComment);
		tpropertyComment.setTproperty(this);

		return tpropertyComment;
	}

	public TpropertyComment removeTpropertyComment(TpropertyComment tpropertyComment) {
		getTpropertyComments().remove(tpropertyComment);
		tpropertyComment.setTproperty(null);

		return tpropertyComment;
	}


	//bi-directional many-to-one association to TpropertyRating
	@OneToMany(mappedBy="tproperty")
	@JsonIgnore
	public List<TpropertyRating> getTpropertyRatings() {
		return this.tpropertyRatings;
	}

	public void setTpropertyRatings(List<TpropertyRating> tpropertyRatings) {
		this.tpropertyRatings = tpropertyRatings;
	}

	public TpropertyRating addTpropertyRating(TpropertyRating tpropertyRating) {
		getTpropertyRatings().add(tpropertyRating);
		tpropertyRating.setTproperty(this);

		return tpropertyRating;
	}

	public TpropertyRating removeTpropertyRating(TpropertyRating tpropertyRating) {
		getTpropertyRatings().remove(tpropertyRating);
		tpropertyRating.setTproperty(null);

		return tpropertyRating;
	}


	//bi-directional many-to-many association to Tuser
	@ManyToMany(mappedBy="tproperties2")
	@JsonIgnore
	public List<Tuser> getTusers() {
		return this.tusers;
	}

	public void setTusers(List<Tuser> tusers) {
		this.tusers = tusers;
	}


	//bi-directional many-to-one association to TpropertyImage
	@OneToMany(mappedBy="tproperty")
	public List<TpropertyImage> getTpropertyImages() {
		return this.tpropertyImages;
	}

	public void setTpropertyImages(List<TpropertyImage> tpropertyImages) {
		this.tpropertyImages = tpropertyImages;
	}

	public TpropertyImage addTpropertyImage(TpropertyImage tpropertyImage) {
		getTpropertyImages().add(tpropertyImage);
		tpropertyImage.setTproperty(this);

		return tpropertyImage;
	}

	public TpropertyImage removeTpropertyImage(TpropertyImage tpropertyImage) {
		getTpropertyImages().remove(tpropertyImage);
		tpropertyImage.setTproperty(null);

		return tpropertyImage;
	}
	@Column(name="offer_percentage")
	public double getOfferPecentage() {
		return offerPecentage;
	}


	public void setOfferPecentage(double offerPecentage) {
		this.offerPecentage = offerPecentage;
	}

	@Column(name="is_sold")
	public byte getIsSold() {
		return isSold;
	}


	public void setIsSold(byte isSold) {
		this.isSold = isSold;
	}

	@Column(name="sold_date")
	public Date getSoldDate() {
		return soldDate;
	}


	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	@Column(name="sale_type")
	public String getSaleType() {
		return saleType;
	}


	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	@Column(name="total_views")
	public int getTotalViews() {
		return totalViews;
	}


	public void setTotalViews(int totalViews) {
		this.totalViews = totalViews;
	}
	
	
	

}
