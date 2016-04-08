package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the tproperty_comments database table.
 * 
 */
@Entity
@Table(name="tproperty_reviews")
@NamedQuery(name="TpropertyReview.findAll", query="SELECT t FROM TpropertyReview t")
public class TpropertyReview implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idReview;
	private String comment;
	private Date registrationDate;
	private Tproperty tproperty;
	private Tuser tuser;
	private double averageRating;
	
	public TpropertyReview() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_review")
	public int getIdReview() {
		return this.idReview;
	}

	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}


	@Lob
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	//bi-directional many-to-one association to Tproperty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_property")
	@JsonIgnore
	public Tproperty getTproperty() {
		return this.tproperty;
	}

	public void setTproperty(Tproperty tproperty) {
		this.tproperty = tproperty;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	@JsonIgnore
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	@Column(name="average_rating")
	public double getAverageRating() {
		return averageRating;
	}


	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	
}