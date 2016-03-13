package com.techflow.propiedadesCR.contracts;

import java.util.ArrayList;

import com.techflow.propiedadesCR.pojo.PropertyPOJO;

public class PropertiesRequest extends BaseRequest {
	
	PropertyPOJO property;
	ArrayList<Integer> idBenefits;
	
	public PropertiesRequest() {
		super();
	}

	public PropertyPOJO getProperty() {
		return property;
	}

	public void setProperty(PropertyPOJO property) {
		this.property = property;
	}
	
	public ArrayList<Integer> getIdBenefits() {
		return idBenefits;
	}

	public void setIdBenefits(ArrayList<Integer> idBenefits) {
		this.idBenefits = idBenefits;
	}

	@Override
	public String toString() {
		return "PropertiesRequest [property=" + property + "]";
	}
}
