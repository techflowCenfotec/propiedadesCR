package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.DistrictRequest;
import com.techflow.propiedadesCR.contracts.DistrictResponse;
import com.techflow.propiedadesCR.services.DistrictServiceInterface;

/**
* <h1>DistrictController</h1>
* Controller that sends or request information
* about the districts through the service
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/

@RestController
@RequestMapping(value="rest/protected/districts")
public class DistrictController {

	@Autowired private DistrictServiceInterface districtService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public DistrictResponse getAll(@RequestBody DistrictRequest dr) {
		DistrictResponse response = new DistrictResponse();
		
		response.setCode(200);
		response.setCodeMessage("District fetch succesful");
		response.setDistricts(districtService.getAll(dr));
		
		return response;
	}
}
