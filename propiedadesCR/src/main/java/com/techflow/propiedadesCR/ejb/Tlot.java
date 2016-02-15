package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tlots database table.
 * 
 */
@Entity
@Table(name="tlots")
@NamedQuery(name="Tlot.findAll", query="SELECT t FROM Tlot t")
public class Tlot implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idLot;
	private byte constructionPermit;
	private byte soilSurvey;
	private String soilType;
	private byte waterPermit;
	private Tproperty tproperty;

	public Tlot() {
	}


	@Id
	@Column(name="id_lot")
	public int getIdLot() {
		return this.idLot;
	}

	public void setIdLot(int idLot) {
		this.idLot = idLot;
	}


	@Column(name="construction_permit")
	public byte getConstructionPermit() {
		return this.constructionPermit;
	}

	public void setConstructionPermit(byte constructionPermit) {
		this.constructionPermit = constructionPermit;
	}


	@Column(name="soil_survey")
	public byte getSoilSurvey() {
		return this.soilSurvey;
	}

	public void setSoilSurvey(byte soilSurvey) {
		this.soilSurvey = soilSurvey;
	}


	@Column(name="soil_type")
	public String getSoilType() {
		return this.soilType;
	}

	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}


	@Column(name="water_permit")
	public byte getWaterPermit() {
		return this.waterPermit;
	}

	public void setWaterPermit(byte waterPermit) {
		this.waterPermit = waterPermit;
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

}