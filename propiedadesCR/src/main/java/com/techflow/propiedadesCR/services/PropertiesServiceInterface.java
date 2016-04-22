package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import com.techflow.propiedadesCR.contracts.PropertiesRequest;
import com.techflow.propiedadesCR.contracts.PropertiesResponse;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;

public interface PropertiesServiceInterface {
	
	ArrayList<PropertyPOJO> getPropertiesWithBenefits();
	PropertiesResponse getAll(PropertiesRequest pPropertiesRequest);
	List<PropertyPOJO> getAllProperties();
	Tproperty getPropertyById(int pIdProperty);
	PropertyPOJO getByPropertyId(int pIdProperty);
	Tproperty saveProperty(PropertiesRequest pProperty);
	Tproperty setPropertyOnSale(PropertiesRequest ppropertyRequest);
	Tproperty updateProperty(PropertiesRequest pProperty, int pIdProperty);
	void deleteProperty(Tproperty pProperty);
	PropertyPOJO propertyViews(PropertiesRequest prequest);

	Tproperty setPropertySold(PropertiesRequest pProperty);
	PropertiesResponse getPropertiesByIdVendor(PropertiesRequest pPropertiesRequest);
	List<PropertyPOJO> getPropertiesVendor(PropertiesRequest pPropertiesRequest);
}
