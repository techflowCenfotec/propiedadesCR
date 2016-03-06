package com.techflow.propiedadesCR.pojo;

import java.util.List;

public class CountyPOJO {

	private int idCounty;
	private int code;
	private String name;
	private ProvincePOJO tprovince;
	private List<DistrictPOJO> tdistricts;
	
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

	public ProvincePOJO getTprovince() {
		return tprovince;
	}

	public void setTprovince(ProvincePOJO tprovince) {
		this.tprovince = tprovince;
	}

	public List<DistrictPOJO> getTdistricts() {
		return tdistricts;
	}

	public void setTdistricts(List<DistrictPOJO> tdistricts) {
		this.tdistricts = tdistricts;
	}
}
