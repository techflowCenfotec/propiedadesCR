package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.ProvinceRequest;
import com.techflow.propiedadesCR.contracts.ProvinceResponse;
import com.techflow.propiedadesCR.services.ProvinceServiceInterface;

/**
* <h1>ProvinceController</h1>
* Controller that request information
* about the provinces through the service
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value = "rest/protected/province")
public class ProvinceController {

	@Autowired private ProvinceServiceInterface provinceService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.POST )
	public ProvinceResponse getAll(ProvinceRequest pr) {
		ProvinceResponse response = new ProvinceResponse();
		
		response.setCode(200);
		response.setCodeMessage("Provinces fetch succesful");
		response.setProvinces(provinceService.getAll(pr));
		
		return response;
	}
}
