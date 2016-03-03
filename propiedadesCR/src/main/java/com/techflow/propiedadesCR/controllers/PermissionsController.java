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
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/permissions")
public class PermissionsController {
	@Autowired private PermissionsServiceInterface permissionsService;
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	
	public PermissionsResponse getAll(@RequestBody PermissionsRequest rs){	
		PermissionsResponse rl = new PermissionsResponse();
		rl.setCode(200);
		rl.setCodeMessage("permissions fetch success");
		rl.setPermission(permissionsService.getAll(rs)); 
		return rl;		
	}

}
