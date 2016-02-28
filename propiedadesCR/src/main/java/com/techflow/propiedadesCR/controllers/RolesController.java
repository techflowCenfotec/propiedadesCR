package com.techflow.propiedadesCR.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.RolesRequest;
import com.techflow.propiedadesCR.contracts.RolesResponse;
import com.techflow.propiedadesCR.services.RolesServiceInterface;

@RestController
@RequestMapping(value="rest/protected/roles")//properties?

public class RolesController {

	@Autowired private RolesServiceInterface rolesService;
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	
	public RolesResponse getAll(@RequestBody RolesRequest rs){	
		RolesResponse rl = new RolesResponse();
		rl.setCode(200);
		rl.setCodeMessage("users fetch success");
		rl.setRole(rolesService.getAll(rs)); 
		return rl;		
	}
	
	@RequestMapping(value ="/getRoleById", method = RequestMethod.POST)
	public RolesResponse getPermissions(@RequestBody RolesRequest rs){	
			
		RolesResponse rl = new RolesResponse();
		rl.setCode(200);
		rl.setCodeMessage("users fetch success");
		rl.setRole(rolesService.getRoleAndPermissions(rs));
		return rl;		
	}
}
