package com.techflow.propiedadesCR.controllers;

import java.util.Arrays;

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
import com.techflow.propiedadesCR.services.BenefitsServiceInterface;
import com.techflow.propiedadesCR.services.PropertiesServiceInterface;
import com.techflow.propiedadesCR.services.PropertyTypeServiceInterface;
import com.techflow.propiedadesCR.services.ProvinceServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

@RestController
@RequestMapping(value="rest/protected/properties")
public class PropertiesController {

	@Autowired private ServletContext servletContext;
	@Autowired private ProvinceServiceInterface provinceService;
	@Autowired private PropertyTypeServiceInterface propertyTypeService;
	@Autowired private PropertiesServiceInterface propertiesService;
	@Autowired private BenefitsServiceInterface benefitsService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public PropertiesResponse getAll() {
		PropertiesResponse response = new PropertiesResponse();
		response.setCode(200);
		response.setCodeMessage("Properties fetch successful");
		response.setProperties(propertiesService.getAll());
		
		return response;
	}
	
	/**
	  * Method that submits property information through service
	  * @param pr Parameter for the saveProperty method of the service
	  * @return Returns object created.
	  */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public PropertiesResponse create(@RequestParam("location") String pLocation,
									 @RequestParam("nearby_areas") String pNearbyAreas,
									 @RequestParam("square_meters") double pSquareMeters,
									 @RequestParam("price") double pPrice,
									 @RequestParam("idProvince") int pIdProvince,
									 @RequestParam("benefits") String pBenefits,
									 @RequestParam("id_property_type") int pIdPropertyType,
									 @RequestParam("file") MultipartFile pPropertyImage) {
		PropertiesResponse response = new PropertiesResponse();
		String resultFileName = Utils.writeToFile(pPropertyImage, servletContext);
		
		if (!resultFileName.equals("")) {
			
			int[] ben = Arrays.stream(pBenefits.substring(1, pBenefits.length() -1)
					.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
			
			Tproperty property = new Tproperty();
			
			property.setLocation(pLocation);
			property.setNearbyAreas(pNearbyAreas);
			property.setSquareMeters(pSquareMeters);
			property.setPrice(pPrice);
			property.setTprovince(provinceService.getProvinceById(pIdProvince));
			property.setActive((byte)1);
			property.setTpropertyType(propertyTypeService.getPropertyTypeById(pIdPropertyType));
			property.setPropertyImage(resultFileName);
			property.setTbenefits(benefitsService.getBenefits(ben));
			
			Tproperty nProperty = propertiesService.saveProperty(property);
			
			if(nProperty!=null){
				response.setCode(200);
				response.setCodeMessage("Property created succesfully!");
			}
		} else {
			response.setCode(400);
			response.setCodeMessage("Could not register property");
		}
		return response;
	}
}
