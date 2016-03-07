package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.ProvinceResponse;
import com.techflow.propiedadesCR.services.ProvinceServiceInterface;

/**
* <h1>Controlador de las provincias</h1>
* Controlador que envía o solicita información a través del servicio.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value = "rest/protected/province")
public class ProvinceController {

	/**
	 * Atributo de la interfaz de las provincias.
	 */
	@Autowired private ProvinceServiceInterface provinceService;
	
	/**
	 * Solicita la información de las provincias a través del servicio.
	 *  
	 * @return response - un objeto response de los tipos de provincia.
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ProvinceResponse getAll() {
		ProvinceResponse response = new ProvinceResponse();
		
		response.setCode(200);
		response.setCodeMessage("Provinces fetch succesful");
		response.setProvinces(provinceService.getAll());
		
		return response;
	}
}
