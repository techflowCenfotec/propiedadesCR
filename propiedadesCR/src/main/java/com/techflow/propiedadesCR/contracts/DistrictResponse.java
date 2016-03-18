package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.DistrictPOJO;

public class DistrictResponse extends BaseResponse {

	List<DistrictPOJO> districts;
	DistrictPOJO district;
	
	public DistrictResponse() {
		super();
	}

	public List<DistrictPOJO> getDistricts() {
		return districts;
	}

	public void setDistricts(List<DistrictPOJO> districts) {
		this.districts = districts;
	}

	public DistrictPOJO getDistrict() {
		return district;
	}

	public void setDistrict(DistrictPOJO district) {
		this.district = district;
	}
}
