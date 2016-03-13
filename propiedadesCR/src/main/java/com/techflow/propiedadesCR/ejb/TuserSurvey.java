package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tuser_surveys database table.
 * 
 */
@Entity
@Table(name="tuser_surveys")
@NamedQuery(name="TuserSurvey.findAll", query="SELECT t FROM TuserSurvey t")
public class TuserSurvey implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idSurvey;
	private List<Tanswer> tanswers;
	private Tuser tuser;

	public TuserSurvey() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_survey")
	public int getIdSurvey() {
		return this.idSurvey;
	}

	public void setIdSurvey(int idSurvey) {
		this.idSurvey = idSurvey;
	}


	//bi-directional many-to-one association to Tanswer
	@OneToMany(mappedBy="tuserSurvey")
	public List<Tanswer> getTanswers() {
		return this.tanswers;
	}

	public void setTanswers(List<Tanswer> tanswers) {
		this.tanswers = tanswers;
	}

	public Tanswer addTanswer(Tanswer tanswer) {
		getTanswers().add(tanswer);
		tanswer.setTuserSurvey(this);

		return tanswer;
	}

	public Tanswer removeTanswer(Tanswer tanswer) {
		getTanswers().remove(tanswer);
		tanswer.setTuserSurvey(null);

		return tanswer;
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