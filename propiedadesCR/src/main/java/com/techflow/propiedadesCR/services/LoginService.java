
/**
* <h1>Servicio del login</h1>
* 
* Esta clase se encarga de realizar la 
* comunicación entre el backend y el frontend.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 3/5/2016
*/
package com.techflow.propiedadesCR.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.techflow.propiedadesCR.contracts.LoginRequest;
import com.techflow.propiedadesCR.repositories.LoginRepository;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;

@Service
public class LoginService implements LoginServiceInterface {

	@Autowired
	LoginRepository repositoryLogin;

	/**
	    * Este método retorna el usuario que esta iniciando sesión
	    *
	    * @param ploginRequest Este parámetro encapsula la información solicitada en el metodo.
		*
	    * @return response Retorna la respuesta del repositorio al controlador.
	    */	
	@Override
	public UserPOJO checkUser(LoginRequest ploginRequest) {
		UserPOJO userPOJO = null;
		Tuser nuser = repositoryLogin.findByEmailAndPassword(ploginRequest.getUserName(), ploginRequest.getPassword());

		if (null != nuser){
			userPOJO = new UserPOJO();
			BeanUtils.copyProperties(nuser, userPOJO);
		}	
		return userPOJO;
	}

}
