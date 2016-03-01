package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;
import com.techflow.propiedadesCR.repositories.PropertyTypeRepository;

/**
* <h1>PropertyTypeService</h1>
* Service for the property type.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class PropertyTypeService implements PropertyTypeServiceInterface {

	@Autowired private PropertyTypeRepository pTypeRepository;
	
	@Override
	@Transactional
	public List<PropertyTypePOJO> getAll() {
		List<TpropertyType> pTypes =  pTypeRepository.findAll();
		return generatePTypeDtos(pTypes);
	}
	
	private List<PropertyTypePOJO> generatePTypeDtos(List<TpropertyType> pTypes) {
		List<PropertyTypePOJO> uiPTypes = new ArrayList<PropertyTypePOJO>();
		pTypes.stream().forEach(u -> {
			PropertyTypePOJO dto = new PropertyTypePOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTproperties(null);
			dto.setTresidences(null);
			uiPTypes.add(dto);
		});
		
		return uiPTypes;
	}
	
	@Override
	public TpropertyType getPropertyTypeById(int idPropertyType) {
		return pTypeRepository.findOne(idPropertyType);
	}
}
