package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.ejb.Tprovince;

public class CountyPOJO {

	private int idCounty;
	private int code;
	private String name;
	private Tprovince tprovince;
	private List<Tdistrict> tdistricts;
	
	public CountyPOJO() {
		super();
	}
	public int getIdCounty() {
		return idCounty;
	}
	public void setIdCounty(int idCounty) {
		this.idCounty = idCounty;
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
	public Tprovince getTprovince() {
		return tprovince;
	}
	public void setTprovince(Tprovince tprovince) {
		this.tprovince = tprovince;
	}
	public List<Tdistrict> getTdistricts() {
		return tdistricts;
	}
	public void setTdistricts(List<Tdistrict> tdistricts) {
		this.tdistricts = tdistricts;
	}
}
