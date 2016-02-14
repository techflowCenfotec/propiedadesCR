package com.techflow.propiedadesCR.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.contracts.UsersResponse;
import com.techflow.propiedadesCR.services.UsersServiceInterface;

@RestController
@RequestMapping(value="rest/protected/users")
public class UsersController {

	@Autowired private UsersServiceInterface usersService;
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value="/getAll", method=RequestMethod.POST)
	public UsersResponse getAll(@RequestBody UsersRequest ur) {
		UsersResponse response = new UsersResponse();
		response.setCode(200);
		response.setCodeMessage("User fetch successful");
		response.setUsuarios(usersService.getAll(ur));
		
		return response;
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public UsersResponse create(@RequestBody UsersRequest ur){	
		
		UsersResponse us = new UsersResponse();
		Boolean state = usersService.saveUser(ur);
	
		if(state){
			us.setCode(200);
			us.setCodeMessage("user created succesfully");
		}
		return us;
		
	}
}
