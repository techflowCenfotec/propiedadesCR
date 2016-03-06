package com.techflow.propiedadesCR.pojo;

import java.util.List;

public class ProvincePOJO {

	private int idProvince;
	private String code;
	private String name;
	private List<CountyPOJO> tcounties;
	private List<PropertyPOJO> tproperties;
	
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

	public List<CountyPOJO> getTcounties() {
		return tcounties;
	}

	public void setTcounties(List<CountyPOJO> tcounties) {
		this.tcounties = tcounties;
	}

	public List<PropertyPOJO> getTproperties() {
		return tproperties;
	}

	public void setTproperties(List<PropertyPOJO> tproperties) {
		this.tproperties = tproperties;
	}
}
