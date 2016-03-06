package com.techflow.propiedadesCR.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.LoginRequest;
import com.techflow.propiedadesCR.contracts.LoginResponse;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.services.LoginServiceInterface;

@RestController
@RequestMapping(value="rest/login")
public class LoginController {

	@Autowired private LoginServiceInterface loginService;
	@Autowired private HttpServletRequest httpRequest;
	
	
	@RequestMapping(value="/checkUser", method = RequestMethod.POST)
	public LoginResponse getAll(@RequestBody LoginRequest ploginRequest) {
		
		LoginResponse response = new LoginResponse();
		
		UserPOJO userLogged = loginService.checkUser(ploginRequest);
		
		response.setUser(userLogged);
		httpRequest.getSession().setAttribute("userLogged", userLogged);
		return response;
	}
	
}
