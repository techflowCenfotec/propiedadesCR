package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;

public class PropertyTypeResponse extends BaseResponse {
	
	List<PropertyTypePOJO> pTypes;
	
	public PropertyTypeResponse(){
		super();
	}

	public List<PropertyTypePOJO> getpTypes() {
		return pTypes;
	}

	public void setpTypes(List<PropertyTypePOJO> pTypes) {
		this.pTypes = pTypes;
	}
}
