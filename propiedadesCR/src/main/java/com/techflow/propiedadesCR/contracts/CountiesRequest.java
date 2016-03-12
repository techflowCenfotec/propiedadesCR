package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.CountyPOJO;

public class CountiesRequest extends BaseRequest {

	CountyPOJO county;

	public CountyPOJO getCounty() {
		return county;
	}

	public void setCounty(CountyPOJO county) {
		this.county = county;
	}
	
	@Override
	public String toString(){
		return "CountyRequest [county= " + county + "]";
	}
	
}
