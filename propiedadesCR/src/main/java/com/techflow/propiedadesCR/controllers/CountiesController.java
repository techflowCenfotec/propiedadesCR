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
* Controlador que envia o solicita informacion a traves del servicio.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value="rest/protected/counties")
public class CountiesController {

	/**
	 * Atributo de la interfaz de los cantones.
	 */
	@Autowired private CountiesServiceInterface countiesService;
	
	/**
	 * Solicita la informacion de los cantones a traves del servicio.
	 *  
	 * @return response Un objeto response de los cantones.
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
