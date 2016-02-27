package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.techflow.propiedadesCR.contracts.PropertyTypeRequest;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;
import com.techflow.propiedadesCR.pojo.ProvincePOJO;
import com.techflow.propiedadesCR.repositories.PropertyTypeRepository;

/**
* <h1>PropertyTypeService</h1>
* Service for the property type.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class PropertyTypeService implements PropertyTypeServiceInterface {

	@Autowired private PropertyTypeRepository pTypeRepository;
	
	@Override
	public List<PropertyTypePOJO> getAll(PropertyTypeRequest ptr) {
		List<TpropertyType> pTypes =  pTypeRepository.findAll();
		return generatePTypeDtos(pTypes);
	}
	
	private List<PropertyTypePOJO> generatePTypeDtos(List<TpropertyType> pTypes) {
		List<PropertyTypePOJO> uiPTypes = new ArrayList<PropertyTypePOJO>();
		pTypes.stream().forEach(u -> {
			PropertyTypePOJO dto = new PropertyTypePOJO();
			BeanUtils.copyProperties(u, dto);
			uiPTypes.add(dto);
		});
		
		return uiPTypes;
	}
	
	@Override
	public TpropertyType getPropertyById(int idPropertyType) {
		return pTypeRepository.findOne(idPropertyType);
	}

	

}
