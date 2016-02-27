package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tcounty;
import com.techflow.propiedadesCR.ejb.Tproperty;

public class ProvincePOJO {

	private int idProvince;
	private String code;
	private String name;
	private List<Tcounty> tcounties;
	private List<Tproperty> tproperties;
	
	public ProvincePOJO() {
		super();
	}
	
	public int getIdProvince() {
		return idProvince;
	}
	public void setIdProvince(int idProvince) {
		this.idProvince = idProvince;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Tcounty> getTcounties() {
		return tcounties;
	}

	public void setTcounties(List<Tcounty> tcounties) {
		this.tcounties = tcounties;
	}

	public List<Tproperty> getTproperties() {
		return tproperties;
	}

	public void setTproperties(List<Tproperty> tproperties) {
		this.tproperties = tproperties;
	}
}
