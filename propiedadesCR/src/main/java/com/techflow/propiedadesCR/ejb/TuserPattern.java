package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tuser_patterns database table.
 * 
 */
@Entity
@Table(name="tuser_patterns")
@NamedQuery(name="TuserPattern.findAll", query="SELECT t FROM TuserPattern t")
public class TuserPattern implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idPattern;
	private String pricePattern;
	private String searchPattern;
	private Tuser tuser;

	public TuserPattern() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_pattern")
	public int getIdPattern() {
		return this.idPattern;
	}

	public void setIdPattern(int idPattern) {
		this.idPattern = idPattern;
	}


	@Column(name="price_pattern")
	public String getPricePattern() {
		return this.pricePattern;
	}

	public void setPricePattern(String pricePattern) {
		this.pricePattern = pricePattern;
	}


	@Column(name="search_pattern")
	public String getSearchPattern() {
		return this.searchPattern;
	}

	public void setSearchPattern(String searchPattern) {
		this.searchPattern = searchPattern;
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