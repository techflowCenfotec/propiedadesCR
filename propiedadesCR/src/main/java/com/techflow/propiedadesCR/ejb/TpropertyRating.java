package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tproperty_ratings database table.
 * 
 */
@Entity
@Table(name="tproperty_ratings")
@NamedQuery(name="TpropertyRating.findAll", query="SELECT t FROM TpropertyRating t")
public class TpropertyRating implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idRating;
	private double averageRating;
	private Tproperty tproperty;
	private Tuser tuser;

	public TpropertyRating() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_rating")
	public int getIdRating() {
		return this.idRating;
	}

	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}


	@Column(name="average_rating")
	public double getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
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


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

}