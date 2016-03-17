package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.CountyPOJO;

public class CountiesResponse extends BaseResponse {

	List<CountyPOJO> counties;
	CountyPOJO county;

	public CountiesResponse() {
		super();
	}
	public List<CountyPOJO> getCounties() {
		return counties;
	}

	public void setCounties(List<CountyPOJO> counties) {
		this.counties = counties;
	}
	
	public CountyPOJO getCounty() {
		return county;
	}
	
	public void setCounty(CountyPOJO county) {
		this.county = county;
	}
}
