package com.techflow.propiedadesCR.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techflow.propiedadesCR.contracts.PropertiesRequest;
import com.techflow.propiedadesCR.contracts.PropertiesResponse;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.services.PropertiesServiceInterface;
import com.techflow.propiedadesCR.services.PropertyTypeServiceInterface;
import com.techflow.propiedadesCR.services.ProvinceServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

/**
* <h1>PropertiesController</h1>
* Java controller that sends or request information
* about the properties through the service
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value="rest/protected/properties")
public class PropertiesController {

	@Autowired private ServletContext servletContext;
	@Autowired private ProvinceServiceInterface provinceService;
	@Autowired private PropertyTypeServiceInterface propertyTypeService;
	@Autowired private PropertiesServiceInterface propertiesService;
	
	/**
	  * Request all properties information through the service.
	  *  
	  * @param pr Parameter for the getAll method of the service
	  * @return a property response object.
	  */
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public PropertiesResponse getAll(@RequestBody PropertiesRequest pr) {
		
		PropertiesResponse response = new PropertiesResponse();
		response.setCode(200);
		response.setCodeMessage("Properties fetch successful");
		response.setProperties(propertiesService.getAll(pr));
		
		return response;
	}
	
	/**
	  * Method that submits property information through service 
	  * @param pr Parameter for the saveProperty method of the service
	  * @return Returns object created.
	  */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public PropertiesResponse create(@RequestParam("id_property") int idProperty,
									 @RequestParam("location") String location,
									 @RequestParam("nearby_areas") String nearbyAreas,
									 @RequestParam("square_meters") double squareMeters,
									 @RequestParam("price") double price,
									 @RequestParam("id_province") int idProvince,
									 @RequestParam("active") byte active,
									 @RequestParam("id_property_type") int id_propertyType,
									 @RequestParam("file") MultipartFile file) {
		PropertiesResponse pr = new PropertiesResponse();
		String resultFileName = Utils.writeToFile(file, servletContext);
		
		if (resultFileName.equals("")) {
			
			Tproperty property = new Tproperty();
			property.setIdProperty(idProperty);
			property.setLocation(location);
			property.setNearbyAreas(nearbyAreas);
			property.setSquareMeters(squareMeters);
			property.setPrice(price);
			property.setTprovince(provinceService.getProvinceById(idProvince));
			property.setActive(active);
			property.setTpropertyType();
			
		}
		
		return null;
	}
}
