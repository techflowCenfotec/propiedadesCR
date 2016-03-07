package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techflow.propiedadesCR.contracts.PropertyTypeResponse;
import com.techflow.propiedadesCR.services.PropertyTypeServiceInterface;

/**
* <h1>Controlador de los tipos de propiedades</h1>
* Controlador que envía o solicita información a través del servicio.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@RestController
@RequestMapping(value = "rest/protected/propertyTypes")
public class PropertyTypeController {

	/**
	 * Atributo de la interfaz de los tipos de propiedades.
	 */
	@Autowired PropertyTypeServiceInterface pTypeService;
	
	/**
	 * Solicita la información de los tipos de propiedades a través del servicio.
	 *  
	 * @return response - un objeto response de los tipos de propiedad.
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public PropertyTypeResponse getAll() {
		PropertyTypeResponse response = new PropertyTypeResponse();
		
		response.setCode(200);
		response.setCodeMessage("Property Type fetch succesful");
		response.setpTypes(pTypeService.getAll());

		return response;
	}
}
