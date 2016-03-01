package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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


	@Lob
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	@Column(name="nearby_areas")
	public String getNearbyAreas() {
		return this.nearbyAreas;
	}

	public void setNearbyAreas(String nearbyAreas) {
		this.nearbyAreas = nearbyAreas;
	}


	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	@Column(name="property_image")
	public String getPropertyImage() {
		return this.propertyImage;
	}

	public void setPropertyImage(String propertyImage) {
		this.propertyImage = propertyImage;
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
	public List<Tbenefit> getTbenefits() {
		return this.tbenefits;
	}

	public void setTbenefits(List<Tbenefit> tbenefits) {
		this.tbenefits = tbenefits;
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


	//bi-directional many-to-one association to Tprovince
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_province")
	public Tprovince getTprovince() {
		return this.tprovince;
	}

	public void setTprovince(Tprovince tprovince) {
		this.tprovince = tprovince;
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


	//bi-directional many-to-one association to Tresidence
	@OneToMany(mappedBy="tproperty")
	public List<Tresidence> getTresidences() {
		return this.tresidences;
	}

	public void setTresidences(List<Tresidence> tresidences) {
		this.tresidences = tresidences;
	}

	public Tresidence addTresidence(Tresidence tresidence) {
		getTresidences().add(tresidence);
		tresidence.setTproperty(this);

		return tresidence;
	}

	public Tresidence removeTresidence(Tresidence tresidence) {
		getTresidences().remove(tresidence);
		tresidence.setTproperty(null);

		return tresidence;
	}


	//bi-directional many-to-many association to Tuser
	@ManyToMany(mappedBy="tproperties")
	public List<Tuser> getTusers() {
		return this.tusers;
	}

	public void setTusers(List<Tuser> tusers) {
		this.tusers = tusers;
	}

}