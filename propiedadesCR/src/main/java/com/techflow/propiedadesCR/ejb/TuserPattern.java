package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the tuser_patterns database table.
 * 
 */
@Entity
@Table(name="tuser_patterns")
@NamedQuery(name="TuserPattern.findAll", query="SELECT t FROM TuserPattern t")
public class TuserPattern implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idUser;
	private String pricePattern;
	private String searchPattern;
	private Tuser tuser;

	public TuserPattern() {
	}


	@Id
	@Column(name="id_user")
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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


	//bi-directional one-to-one association to Tuser
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

}