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
* <h1>Controlador de los beneficios</h1>
* Controlador que solicita información a través del servicio.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/

@RestController
@RequestMapping(value= "rest/protected/benefits")
public class BenefitsController {

	/**
	 * Atributo de la interfaz de los beneficios.
	 */
	@Autowired private BenefitsServiceInterface benefitsService;
	
	/**
	 * Solicita la información de los beneficios a través del servicio.
	 *  
	 * @return response Un objeto response de los beneficios.
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
