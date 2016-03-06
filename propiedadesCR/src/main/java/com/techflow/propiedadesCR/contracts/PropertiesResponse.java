package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.PropertyPOJO;

public class PropertiesResponse extends BaseResponse {

	private List<PropertyPOJO> properties;
	
	public PropertiesResponse() {
		super();
	}

	public List<PropertyPOJO> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyPOJO> pproperties) {
		this.properties = pproperties;
	}
	
}
