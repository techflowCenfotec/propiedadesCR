package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.CountiesResponse;
import com.techflow.propiedadesCR.pojo.CountyPOJO;
import com.techflow.propiedadesCR.services.CountiesServiceInterface;

/**
* <h1>Controlador de los cantones</h1>
* Controlador que envía o solicita informacion a través del servicio.
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
	 * Solicita la información de los cantones a través del servicio.
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
	
	/**
	 * Solicita la información del cantón a través del servicio.
	 *  
	 *  @param pIdCounty Id del cantón. No debe ser nulo.
	 * @return response Un objeto response del cantón.
	 */
	@RequestMapping(value="getCountyById/{pIdCounty}", method = RequestMethod.GET)
	public CountiesResponse getRequestedCounty(@PathVariable int pIdCounty) {
		CountiesResponse response = new CountiesResponse();
		
		CountyPOJO county = countiesService.getCountyById(pIdCounty);
		
		response.setCounty(county);
		
		return response;
	}
}
