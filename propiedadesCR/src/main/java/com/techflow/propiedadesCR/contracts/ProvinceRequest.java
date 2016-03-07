package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.ProvincePOJO;

public class ProvinceRequest extends BaseRequest {

	ProvincePOJO province;
	
	public ProvinceRequest() {
		super();
	}

	public ProvincePOJO getProvince() {
		return province;
	}

	public void setProvince(ProvincePOJO province) {
		this.province = province;
	}
	
	@Override
	public String toString() {
		return "ProvinceRequest [province= " + province + "]";
	}
}
