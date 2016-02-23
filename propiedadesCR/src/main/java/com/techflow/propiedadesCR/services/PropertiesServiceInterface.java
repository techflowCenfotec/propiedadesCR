package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.PropertiesRequest;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;

public interface PropertiesServiceInterface {
	List<PropertyPOJO> getAll(PropertiesRequest pr);
}
