package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.PropertyTypeResponse;
import com.techflow.propiedadesCR.services.PropertyTypeServiceInterface;

/**
* <h1>PropertiesController</h1>
* Controller that sends or request information
* about the property types through the service
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value = "rest/protected/propertyTypes")
public class PropertyTypeController {

	@Autowired PropertyTypeServiceInterface pTypeService;
	
	/**
	  * Request all property type information through the service.
	  *  
	  * @param pr - Parameter for the getAll method of the service
	  * @return a property response object.
	  */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public PropertyTypeResponse getAll() {
		
		PropertyTypeResponse response = new PropertyTypeResponse();
		response.setCode(200);
		response.setCodeMessage("Property Type fetch succesful");
		response.setpTypes(pTypeService.getAll());
		
		return response;
	}
}
