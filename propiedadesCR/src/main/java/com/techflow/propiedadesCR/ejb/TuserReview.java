package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the tuser_reviews database table.
 * 
 */
@Entity
@Table(name="tuser_reviews")
@NamedQuery(name="TuserReview.findAll", query="SELECT t FROM TuserReview t")
public class TuserReview implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idReview;
	private double averageRating;
	private Tuser tuser1;
	private Tuser tuser2;
	private String comment;
	private Date registrationDate;
	

	public TuserReview() {
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


	@Column(name="average_rating")
	public double getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_client")
	public Tuser getTuser1() {
		return this.tuser1;
	}

	public void setTuser1(Tuser tuser1) {
		this.tuser1 = tuser1;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_seller")
	public Tuser getTuser2() {
		return this.tuser2;
	}

	public void setTuser2(Tuser tuser2) {
		this.tuser2 = tuser2;
	}

	@Lob
	public String getComment() {
		return comment;
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
}