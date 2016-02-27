package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;

public class PropertyTypeRequest extends BaseRequest {
	
	PropertyTypePOJO pType;
	
	public PropertyTypeRequest(){
		super();
	}

	public PropertyTypePOJO getpType() {
		return pType;
	}

	public void setpType(PropertyTypePOJO pType) {
		this.pType = pType;
	}

	@Override
	public String toString() {
		return "PropertyTypeRequest [property type= " + pType + "]";
	}
}
