package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.CountiesRequest;
import com.techflow.propiedadesCR.contracts.CountiesResponse;
import com.techflow.propiedadesCR.services.CountiesServiceInterface;

/**
* <h1>Controlador de los cantones</h1>
* Controlador que envía o solicita información a través del servicio.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value="rest/protected/counties")
public class CountiesController {

	@Autowired private CountiesServiceInterface countiesService;
	
	/**
	 * Solicita la información de los cantones a través del servicio.
	 *  
	 * @return un objeto response de los cantones.
	 */
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public CountiesResponse getAll() {
		CountiesResponse response = new CountiesResponse();
		
		response.setCode(200);
		response.setCodeMessage("Counties fetch succesful");
		response.setCounties(countiesService.getAll());
		
		return response;
	}
}
