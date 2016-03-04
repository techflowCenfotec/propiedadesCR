package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.PermissionsRequest;
import com.techflow.propiedadesCR.contracts.PermissionsResponse;
import com.techflow.propiedadesCR.services.PermissionsServiceInterface;
/**
 * <h1>Controlador de permisos</h1>
 * Esta clase es la encargada de recibir peticiones del front-end
 * y manejar todo lo relacionado con los permisos del sistema
 * 
 *@author Valeria Ramírez
 *@version 1.0
 *@since 02/29/2016
 */

@RestController
@RequestMapping(value ="rest/protected/permissions")
public class PermissionsController {
	/**
	 * Objeto que ofrece servicios de los roles
	 *
	 */ 
	@Autowired private PermissionsServiceInterface permissionsService;
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	
	/**
	* Este método permite obtener todos los permisos que se encuetran
	* en el sistema
	* 
	* @param ppermissionsRequest Este parámetro es la peticion del front-end
	* que se utiliza para acceder al método deseado
	* 
	* @return permissionsResponse Resultado que contiene la lista de permisos que se ecuntran
	* en el sistema
	*
	*/ 
	public PermissionsResponse getAll(@RequestBody PermissionsRequest ppermissionsRequest){	
		PermissionsResponse permissionsResponse = new PermissionsResponse();
		permissionsResponse.setCode(200);
		permissionsResponse.setCodeMessage("permissions fetch success");
		permissionsResponse.setPermission(permissionsService.getAll(ppermissionsRequest)); 
		return permissionsResponse;		
	}

}
