package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;

public interface PropertiesServiceInterface {
	
	List<PropertyPOJO> getAll();
	PropertyPOJO getPropertyById(int pIdProperty);
	Tproperty saveProperty(Tproperty pProperty);
}
