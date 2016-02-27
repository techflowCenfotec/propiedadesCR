package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.ProvincePOJO;

public class ProvinceResponse extends BaseResponse {

	List<ProvincePOJO> provinces;

	public List<ProvincePOJO> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<ProvincePOJO> provinces) {
		this.provinces = provinces;
	}
}
