package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.repositories.PropertiesRepository;

@Service
public class PropertiesService implements PropertiesServiceInterface {

	@Autowired private PropertiesRepository propertiesRepository;
	
	@Override
	@Transactional
	public List<PropertyPOJO> getAll() {
		List<Tproperty> properties = propertiesRepository.findAll();
		return generatePropDtos(properties);
	}
	
	private List<PropertyPOJO> generatePropDtos(List<Tproperty> pProperties) {
		List<PropertyPOJO> uiProperties = new ArrayList<PropertyPOJO>();
		pProperties.stream().forEach(u -> {
			PropertyPOJO dto = new PropertyPOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTpropertyComments(null);
			dto.setTpropertyRatings(null);
			dto.setTusers(null);
			dto.setTbenefits(null);
			dto.setTprovince(null);
			dto.setTpropertyType(null);
			uiProperties.add(dto);
		});
		return uiProperties;
	}

	@Override
	@Transactional
	public Tproperty saveProperty(Tproperty pProperty) {
		Tproperty nProperty =  propertiesRepository.save(pProperty);
		return nProperty;
	}

	@Override
	public Tproperty getPropertyById(int idProperty) {
		return propertiesRepository.findOne(idProperty);
	}

}
