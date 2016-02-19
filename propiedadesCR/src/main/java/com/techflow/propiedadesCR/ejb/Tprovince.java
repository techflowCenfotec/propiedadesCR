package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tprovinces database table.
 * 
 */
@Entity
@Table(name="tprovinces")
@NamedQuery(name="Tprovince.findAll", query="SELECT t FROM Tprovince t")
public class Tprovince implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idProvince;
	private String code;
	private String name;
	private List<Tcounty> tcounties;
	private List<Tproperty> tproperties;

	public Tprovince() {
	}


	@Id
	@Column(name="id_province")
	public int getIdProvince() {
		return this.idProvince;
	}

	public void setIdProvince(int idProvince) {
		this.idProvince = idProvince;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Tcounty
	@OneToMany(mappedBy="tprovince")
	public List<Tcounty> getTcounties() {
		return this.tcounties;
	}

	public void setTcounties(List<Tcounty> tcounties) {
		this.tcounties = tcounties;
	}

	public Tcounty addTcounty(Tcounty tcounty) {
		getTcounties().add(tcounty);
		tcounty.setTprovince(this);

		return tcounty;
	}

	public Tcounty removeTcounty(Tcounty tcounty) {
		getTcounties().remove(tcounty);
		tcounty.setTprovince(null);

		return tcounty;
	}


	//bi-directional many-to-one association to Tproperty
	@OneToMany(mappedBy="tprovince")
	public List<Tproperty> getTproperties() {
		return this.tproperties;
	}

	public void setTproperties(List<Tproperty> tproperties) {
		this.tproperties = tproperties;
	}

	public Tproperty addTproperty(Tproperty tproperty) {
		getTproperties().add(tproperty);
		tproperty.setTprovince(this);

		return tproperty;
	}

	public Tproperty removeTproperty(Tproperty tproperty) {
		getTproperties().remove(tproperty);
		tproperty.setTprovince(null);

		return tproperty;
	}

}