/**
* <h1>Servicio de usuarios</h1> 
* Esta clase  se encarga de comunicarse con el repositorio.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 25/2/2016
*/
package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.security.*;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.repositories.UsersRepository;


@Service
public class UsersService implements UsersServiceInterface{
	
	/**	 
     * El objeto usersRepository es el que se encarga de la comunicación con la BD. 
     */
	@Autowired private UsersRepository usersRepository;
	

	/**
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @param pusersRequest Este parámetro encapsula la información solicitada por el usuario.
	  *
	  * @return uiUsers Retorna la respuesta del repositorio hacia el controlador.
	  */
	@Override
	@Transactional
	public List<UserPOJO> getAll(UsersRequest pusersRequest) {
		List<Tuser> users = usersRepository.findAll();
		return generateUserDtos(users);
	}
	
	/**
	  * Este método genera los objetos POJOS que se retornaran a la UI.
	  *
	  * @param pusers Lista de usuarios.
	  *
	  * @return uiUsers Se retorna una lista de usuarios POJOS.
	  */
	private List<UserPOJO> generateUserDtos(List<Tuser> pusers) {
		List<UserPOJO> uiUsers = new ArrayList<UserPOJO>();
		pusers.stream().forEach(u -> {
			UserPOJO dto = new UserPOJO();
			BeanUtils.copyProperties(u, dto);
			uiUsers.add(dto);
		});
		return uiUsers;
	}
	
	/**
	  * Este método registra un usuario en el sistema.
	  *
	  * @param puserRequest Encapsula los datos requeridos por el usuario.
      * 
	  * @return nuser Retorna el usuario creado.
	  */
	@Override
	public Tuser saveUser(UsersRequest puserRequest) {
		
		Tuser user = new Tuser();
		BeanUtils.copyProperties(puserRequest.getUser(), user);
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			user.setPassword(messageDigest.digest(user.getPassword().getBytes()).toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Tuser nuser = usersRepository.save(user);
		
		return nuser;
	
	}
	
	/**
	  * Este retorna el usaurio que se consulto.
	  *
	  * @param pidUser Identificador del usuario.
      * 
	  * @return userPOJO Retorna el usuario consultado.
	  */
	@Override
	public UserPOJO attendUser(int pidUser){
		
		Tuser nuser = usersRepository.findByIdUser(pidUser);
		UserPOJO userPOJO =null;
		
		if (null != nuser){
			userPOJO = new UserPOJO();
			BeanUtils.copyProperties(nuser, userPOJO);
		}	
		return userPOJO;
		
	}

}
