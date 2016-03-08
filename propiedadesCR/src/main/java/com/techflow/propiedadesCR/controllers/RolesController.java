package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.RolesRequest;
import com.techflow.propiedadesCR.contracts.RolesResponse;
import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.services.RolesServiceInterface;

/**
 * <h1>Controlador de Roles</h1>
 * Esta clase es la encargada de recibir peticiones del front-end
 * y manejar todo lo relacionado con los roles del sistema
 * 
 *@author Valeria Ramírez
 *@version 1.0
 *@since 02/26/2016
 */

@RestController
@RequestMapping(value="rest/protected/roles")//properties?

public class RolesController {

	/**
	 * Objeto que ofrece servicios de los roles
	 *
	 */ 	
	@Autowired private RolesServiceInterface rolesService;
	
	/**
	 * Este método permite obtener todos los roles que se encuetran
	 * en el sistema
	 * 
	 * @param prolesRequest Este parámetro es la peticion del front-end
	 * que se utiliza para acceder al método deseado
	 * 
	 * @return rolesResponse Resultado que contiene la lista de roles que se ecuntran
	 * en el sistema
	 *
	 */ 
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	
	public RolesResponse getAll(@RequestBody RolesRequest prolesRequest){	
		RolesResponse rolesResponse = new RolesResponse();
		rolesResponse.setCode(200);
		rolesResponse.setCodeMessage("roles fetch success");
		rolesResponse.setRole(rolesService.getAll(prolesRequest)); 
		return rolesResponse;		
	}
	/**
	 * Este método permite obtener los datos de un rol en específico
	 * 
	 * @param prolesRequest Este parámetro es la peticion del front-end
	 * que se utiliza para acceder al método deseado
	 * 
	 * @return rolesResponse Resultado que contiene el objeto solicitdado
	 * junto con sus datos
	 *
	 */ 
	@RequestMapping(value ="/getRoleById", method = RequestMethod.POST)
	public RolesResponse getPermissions(@RequestBody RolesRequest prolesRequest){			
		RolesResponse rolesResponse = new RolesResponse();
		rolesResponse.setCode(200);
		rolesResponse.setCodeMessage("roles fetch success");
		rolesResponse.setRole(rolesService.getRoleAndPermissions(prolesRequest));
		return rolesResponse;		
	}
	/**
	* Este método permite agregar un nuevo rol en el sistema
	* 
	* @param prolesRequest Este parámetro es la peticion del front-end
	* que se utiliza para acceder al método deseado
	* 
	* @return rolesResponse Resultado que contiene la respuesta
	* de que el rol haya sido creado exitosamente o no
	*
	*/ 
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public RolesResponse create(@RequestBody RolesRequest prolesRequest){	
		RolesResponse rolesResponse = new RolesResponse();
		Trole  newRole = rolesService.saveRole(prolesRequest);
		if(newRole!= null){
			rolesResponse.setCode(200);
			rolesResponse.setCodeMessage("role created succesfully");
		}
		return rolesResponse;	
	}
}
