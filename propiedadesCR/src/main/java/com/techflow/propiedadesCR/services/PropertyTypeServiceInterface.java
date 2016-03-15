package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.PropertyTypeRequest;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;

public interface PropertyTypeServiceInterface {
	
	List<PropertyTypePOJO> getAll();
	TpropertyType getPropertyTypeById(int pIdPropertyType);
}
