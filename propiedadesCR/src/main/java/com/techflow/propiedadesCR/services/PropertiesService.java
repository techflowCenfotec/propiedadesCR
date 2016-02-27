package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.PropertiesRequest;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.repositories.PropertiesRepository;

@Service
public class PropertiesService implements PropertiesServiceInterface {

	@Autowired private PropertiesRepository propertiesRepository;
	
	@Override
	@Transactional
	public List<PropertyPOJO> getAll(PropertiesRequest pr) {
		List<Tproperty> properties = propertiesRepository.findAll();
		return generatePropDtos(properties);
	}
	
	private List<PropertyPOJO> generatePropDtos(List<Tproperty> properties) {
		List<PropertyPOJO> uiProperties = new ArrayList<PropertyPOJO>();
		properties.stream().forEach(u -> {
			PropertyPOJO dto = new PropertyPOJO();
			BeanUtils.copyProperties(u, dto);
			uiProperties.add(dto);
		});
		return uiProperties;
	}

	@Override
	@Transactional
	public Boolean saveProperty(PropertiesRequest pr) {
		
		Tproperty property = new Tproperty();
		BeanUtils.copyProperties(pr.getProperty(), property);
		Tproperty nProperty =  propertiesRepository.save(property);
		
		return (nProperty == null) ? false : true;
	}

	@Override
	public Tproperty getPropertyById(int idProperty) {
		return propertiesRepository.findOne(idProperty);
	}

}
