package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.DistrictPOJO;

public class DistrictRequest extends BaseRequest {

	DistrictPOJO district;

	public DistrictRequest() {
		super();
	}
	
	public DistrictPOJO getDistrict() {
		return district;
	}

	public void setDistrict(DistrictPOJO district) {
		this.district = district;
	}
	
	@Override
	public String toString() {
		return "DistrictRequest [district= " + district + "]";
	}
}
