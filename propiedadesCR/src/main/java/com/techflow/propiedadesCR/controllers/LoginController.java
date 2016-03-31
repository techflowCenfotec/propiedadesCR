
/**
* <h1>Controlador del login</h1>
* 
* Esta clase se encarga de realizar la 
* comunicación entre el backend y el frontend.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 3/6/2016
*/
package com.techflow.propiedadesCR.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${mail.username}")
	private String gerardo;
	/** 
     * Este objeto proporciona los diferentes servicios para el login.
     */
	@Autowired private LoginServiceInterface loginService;
	/** 
     * Este objeto mantiene la sesión en el backend
     */
	@Autowired private HttpServletRequest httpRequest;
	
	/**
	    * Este método retorna el usuario que esta iniciando sesión
	    *
	    * @param ploginRequest Este parámetro encapsula la información solicitada en el metodo.
		*
	    * @return response Retorna la respuesta del sevicio hacia el frontend.
	    */	
	@RequestMapping(value="/checkUser", method = RequestMethod.POST)
	 public LoginResponse getCheckedUser(@RequestBody LoginRequest ploginRequest,HttpServletRequest servletRequest) {
	  
	  LoginResponse response = new LoginResponse();
	  
	  HttpSession currentSession = servletRequest.getSession();
	  UserPOJO userLogged = loginService.checkUser(ploginRequest,currentSession);
	  
	  //loginService.checkUser(lr,response,currentSession);
	  if(userLogged==null){
	   response.setCode(401);
	   response.setErrorMessage("Unauthorized User");
	  }else{
	   response.setCode(200);
	   response.setUser(userLogged);
	   httpRequest.getSession().setAttribute("userLogged", userLogged);
	   currentSession.setAttribute("idUser", userLogged.getIdUser());
	  }
	  
	  return response;
	 }
	
}
