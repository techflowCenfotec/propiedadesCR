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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the tcounties database table.
 * 
 */
@Entity
@Table(name="tcounties")
@NamedQuery(name="Tcounty.findAll", query="SELECT t FROM Tcounty t")
public class Tcounty implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCounty;
	private int code;
	private String name;
	private Tprovince tprovince;
	private List<Tdistrict> tdistricts;

	public Tcounty() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_county")
	public int getIdCounty() {
		return this.idCounty;
	}

	public void setIdCounty(int idCounty) {
		this.idCounty = idCounty;
	}


	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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


	//bi-directional many-to-one association to Tdistrict
	@OneToMany(mappedBy="tcounty")
	@JsonIgnore
	public List<Tdistrict> getTdistricts() {
		return this.tdistricts;
	}

	public void setTdistricts(List<Tdistrict> tdistricts) {
		this.tdistricts = tdistricts;
	}

	public Tdistrict addTdistrict(Tdistrict tdistrict) {
		getTdistricts().add(tdistrict);
		tdistrict.setTcounty(this);

		return tdistrict;
	}

	public Tdistrict removeTdistrict(Tdistrict tdistrict) {
		getTdistricts().remove(tdistrict);
		tdistrict.setTcounty(null);

		return tdistrict;
	}

}