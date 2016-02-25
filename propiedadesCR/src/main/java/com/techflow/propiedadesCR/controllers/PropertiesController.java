package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.PropertiesRequest;
import com.techflow.propiedadesCR.contracts.PropertiesResponse;
import com.techflow.propiedadesCR.services.PropertiesServiceInterface;

@RestController
@RequestMapping(value="rest/protected/properties")
public class PropertiesController {

	@Autowired private PropertiesServiceInterface propertiesInterface;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public PropertiesResponse getAll(@RequestBody PropertiesRequest pr) {
		
		PropertiesResponse response = new PropertiesResponse();
		response.setCode(200);
		response.setCodeMessage("Properties fetch successful");
		response.setProperties(propertiesInterface.getAll(pr));
		
		return response;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public PropertiesResponse create(@RequestBody PropertiesRequest pr) {
		
		PropertiesResponse response = new PropertiesResponse();
		Boolean state = propertiesInterface.saveProperty(pr);
		
		if(state){
			response.setCode(200);
			response.setCodeMessage("Properties created successful");
		}
		
		return response;
	}
}
