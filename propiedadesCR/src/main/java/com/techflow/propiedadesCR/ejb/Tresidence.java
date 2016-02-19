package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tresidence database table.
 * 
 */
@Entity
@NamedQuery(name="Tresidence.findAll", query="SELECT t FROM Tresidence t")
public class Tresidence implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idResidence;
	private Date dateConstruction;
	private byte garageAvailabity;
	private int garageCapacity;
	private byte greenAreas;
	private int numberBathrooms;
	private int numberFloors;
	private int numberLivingRooms;
	private int numberRooms;
	private double squareMetersConstruction;
	private Tproperty tproperty;
	private TresidenceType tresidenceType;

	public Tresidence() {
	}


	@Id
	@Column(name="id_residence")
	public int getIdResidence() {
		return this.idResidence;
	}

	public void setIdResidence(int idResidence) {
		this.idResidence = idResidence;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="date_construction")
	public Date getDateConstruction() {
		return this.dateConstruction;
	}

	public void setDateConstruction(Date dateConstruction) {
		this.dateConstruction = dateConstruction;
	}


	@Column(name="garage_availabity")
	public byte getGarageAvailabity() {
		return this.garageAvailabity;
	}

	public void setGarageAvailabity(byte garageAvailabity) {
		this.garageAvailabity = garageAvailabity;
	}


	@Column(name="garage_capacity")
	public int getGarageCapacity() {
		return this.garageCapacity;
	}

	public void setGarageCapacity(int garageCapacity) {
		this.garageCapacity = garageCapacity;
	}


	@Column(name="green_areas")
	public byte getGreenAreas() {
		return this.greenAreas;
	}

	public void setGreenAreas(byte greenAreas) {
		this.greenAreas = greenAreas;
	}


	@Column(name="number_bathrooms")
	public int getNumberBathrooms() {
		return this.numberBathrooms;
	}

	public void setNumberBathrooms(int numberBathrooms) {
		this.numberBathrooms = numberBathrooms;
	}


	@Column(name="number_floors")
	public int getNumberFloors() {
		return this.numberFloors;
	}

	public void setNumberFloors(int numberFloors) {
		this.numberFloors = numberFloors;
	}


	@Column(name="number_living_rooms")
	public int getNumberLivingRooms() {
		return this.numberLivingRooms;
	}

	public void setNumberLivingRooms(int numberLivingRooms) {
		this.numberLivingRooms = numberLivingRooms;
	}


	@Column(name="number_rooms")
	public int getNumberRooms() {
		return this.numberRooms;
	}

	public void setNumberRooms(int numberRooms) {
		this.numberRooms = numberRooms;
	}


	@Column(name="square_meters_construction")
	public double getSquareMetersConstruction() {
		return this.squareMetersConstruction;
	}

	public void setSquareMetersConstruction(double squareMetersConstruction) {
		this.squareMetersConstruction = squareMetersConstruction;
	}


	//bi-directional many-to-one association to Tproperty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_property")
	public Tproperty getTproperty() {
		return this.tproperty;
	}

	public void setTproperty(Tproperty tproperty) {
		this.tproperty = tproperty;
	}


	//bi-directional many-to-one association to TresidenceType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_residence_type")
	public TresidenceType getTresidenceType() {
		return this.tresidenceType;
	}

	public void setTresidenceType(TresidenceType tresidenceType) {
		this.tresidenceType = tresidenceType;
	}

}