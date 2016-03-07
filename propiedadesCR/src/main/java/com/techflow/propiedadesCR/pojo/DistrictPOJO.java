package com.techflow.propiedadesCR.pojo;

import com.techflow.propiedadesCR.ejb.Tcounty;

public class DistrictPOJO {

	private int idDisctrict;
	private int code;
	private String name;
	private Tcounty tcounty;
	
	public DistrictPOJO() {
		super();
	}
	public int getIdDisctrict() {
		return idDisctrict;
	}
	public void setIdDisctrict(int idDisctrict) {
		this.idDisctrict = idDisctrict;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Tcounty getTcounty() {
		return tcounty;
	}
	public void setTcounty(Tcounty tcounty) {
		this.tcounty = tcounty;
	}
}
