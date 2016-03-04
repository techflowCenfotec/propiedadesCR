package com.techflow.propiedadesCR.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.PermissionsRequest;
import com.techflow.propiedadesCR.contracts.PermissionsResponse;
import com.techflow.propiedadesCR.contracts.RolesRequest;
import com.techflow.propiedadesCR.contracts.RolesResponse;
import com.techflow.propiedadesCR.services.PermissionsServiceInterface;
/**
 * <h1>Controlador de permisos</h1>
 * Esta clase es la encargada de recibir peticiones del front-end
 * y manejar todo lo relacionado con los roles del sistema
 * 
 *@author Valeria Ramírez
 *@version 1.0
 *@since 03/03/2016
 */

@RestController
@RequestMapping(value ="rest/protected/permissions")
public class PermissionsController {
	@Autowired private PermissionsServiceInterface permissionsService;
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	
	/**
	* Este método permite obtener todos los permisos que se encuetran
	* en el sistema
	* 
	* @param ppermissionsRequest Este parámetro es la peticion del front-end
	* que se utiliza para acceder al método deseado
	* 
	* @return ppermissionsRequest Resultado que contiene la lista de permisos que se ecuntran
	* en el sistema
	*
	*/ 
	public PermissionsResponse getAll(@RequestBody PermissionsRequest ppermissionsRequest){	
		PermissionsResponse permissionsRequest = new PermissionsResponse();
		permissionsRequest.setCode(200);
		permissionsRequest.setCodeMessage("permissions fetch success");
		permissionsRequest.setPermission(permissionsService.getAll(ppermissionsRequest)); 
		return permissionsRequest;		
	}

}
