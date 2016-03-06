package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.DistrictResponse;
import com.techflow.propiedadesCR.services.DistrictServiceInterface;

/**
* <h1>Controlador del Distrito</h1>
* Controlador que envía o solicita información a través del servicio.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/

@RestController
@RequestMapping(value="rest/protected/districts")
public class DistrictController {

	@Autowired private DistrictServiceInterface districtService;
	
	/**
	 * Solicita la información de los distritos a través del servicio.
	 *  
	 * @return un objeto response del distrito.
	 */
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public DistrictResponse getAll() {
		DistrictResponse response = new DistrictResponse();
		
		response.setCode(200);
		response.setCodeMessage("District fetch succesful");
		response.setDistricts(districtService.getAll());
		
		return response;
	}
}
