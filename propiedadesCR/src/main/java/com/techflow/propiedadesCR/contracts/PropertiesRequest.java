package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.PropertyPOJO;

public class PropertiesRequest extends BaseRequest {
	
	PropertyPOJO property;
	
	public PropertiesRequest() {
		super();
	}

	public PropertyPOJO getProperty() {
		return property;
	}

	public void setProperty(PropertyPOJO property) {
		this.property = property;
	}
	
	@Override
	public String toString() {
		return "PropertiesRequest [property=" + property + "]";
	}
}
