package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.PropertyPOJO;

public class UserSurveyMatchResultResponse extends BaseResponse {

	private List<PropertyPOJO> properties;
	private List<Double> porcentages;
	
	public List<PropertyPOJO> getProperties() {
		return properties;
	}
	
	public void setProperties(List<PropertyPOJO> properties) {
		this.properties = properties;
	}
	
	public List<Double> getPorcentages() {
		return porcentages;
	}
	
	public void setPorcentages(List<Double> porcentages) {
		this.porcentages = porcentages;
	}

	
}
