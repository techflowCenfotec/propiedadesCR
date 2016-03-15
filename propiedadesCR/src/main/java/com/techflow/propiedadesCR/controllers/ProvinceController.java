package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.ProvinceResponse;
import com.techflow.propiedadesCR.services.ProvinceServiceInterface;

/**
* <h1>Controlador de las provincias</h1>
* Controlador que env�a o solicita informaci�n a trav�s del servicio.
*
* @author  Walter G�mez
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
	 * Solicita la informaci�n de las provincias a trav�s del servicio.
	 *  
	 * @return response Un objeto response de los tipos de provincia.
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
