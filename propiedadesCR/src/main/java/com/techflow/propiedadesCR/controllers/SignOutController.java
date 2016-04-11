package com.techflow.propiedadesCR.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.BaseResponse;
import com.techflow.propiedadesCR.contracts.LoginRequest;
import com.techflow.propiedadesCR.contracts.LoginResponse;
import com.techflow.propiedadesCR.pojo.UserPOJO;


@RestController
@RequestMapping(value="rest/protected/signOut")
public class SignOutController {
	/**
	 * Este objeto mantiene la sesión en el backend
	 */
	
	@Autowired private HttpServletRequest httpRequest;
	
		/**
	    * Este método retorna el usuario que esta cerrando sesión
	    *@return response Retorna la respuesta del sevicio hacia el frontend.
	    */	
	@RequestMapping(value="/signOut", method = RequestMethod.GET)
	public BaseResponse getSignOut() {
		
		
		
		HttpSession currentSession = httpRequest.getSession();
		BaseResponse response = new LoginResponse();
//		
//		UserPOJO userLogged = loginService.checkUser(ploginRequest);
//	
//		if(userLogged==null){
//			response.setCode(401);
//			response.setErrorMessage("Unauthorized User");
//		}else{
//			response.setCode(200);
//			response.setUser(userLogged);
		httpRequest.getSession().setAttribute("userLogged", null);
		currentSession.setAttribute("idUser", null);
//		}
		
		return response;
	}

}
