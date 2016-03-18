
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.propiedadesCR.contracts.LoginRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.RolePOJO;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.repositories.LoginRepository;

@Service
public class LoginService implements LoginServiceInterface {

	@Autowired
	LoginRepository repositoryLogin;

	/**
	    * Este método retorna el usuario que esta iniciando sesión
	    *
	    * @param ploginRequest Este parámetro encapsula la información solicitada en el metodo.
		*
	    * @return userPOJO Retorna la respuesta del repositorio al controlador.
	    */	
	@Override
	public UserPOJO checkUser(LoginRequest ploginRequest) {
		
		UserPOJO userPOJO = null;
		String password = ploginRequest.getPassword();
		StringBuffer md5password = new StringBuffer();
    
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			 md.update(password.getBytes());
		        
		        byte byteData[] = md.digest();
		 
		        //convert the byte to hex format method 1
		       
		        for (int i = 0; i < byteData.length; i++) {
		         md5password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
		
		Tuser nuser = repositoryLogin.findByEmailAndPassword(ploginRequest.getUserName(), md5password.toString());

		if (null != nuser){
			userPOJO = new UserPOJO();
			userPOJO.setRole(new RolePOJO());
			BeanUtils.copyProperties(nuser, userPOJO);
			BeanUtils.copyProperties(nuser.getTrole(), userPOJO.getRole());
			
		}
		return userPOJO;
	
	}
	
	

}
