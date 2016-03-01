package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.BenefitsRequest;
import com.techflow.propiedadesCR.contracts.BenefitsResponse;
import com.techflow.propiedadesCR.services.BenefitsServiceInterface;

/**
* <h1>BenefitsController</h1>
* Controller that sends or request information
* about the benefits through the service
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/

@RestController
@RequestMapping(value= "rest/protected/benefits")
public class BenefitsController {

	@Autowired private BenefitsServiceInterface benefitsService;
	
	/**
	  * Request all benefits entities through the service.
	  *  
	  * @param pr Parameter for the getAll method of the service
	  * @return a property response object.
	  */
	@RequestMapping(value= "/getAll", method = RequestMethod.GET)
	public BenefitsResponse getAll() {
		
		BenefitsResponse response = new BenefitsResponse();
		
		response.setCode(200);
		response.setCodeMessage("Benefits fetch succesfull");
		response.setBenefits(benefitsService.getAll());
		
		return response;
	}
}
