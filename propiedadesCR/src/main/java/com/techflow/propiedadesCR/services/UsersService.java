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

/**
* <h1>UsersService</h1> 
* Descripción de la clase.
* El servicio es el encargado de comunicarse con el repositorio.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 25/2/2016
*/
@Service
public class UsersService implements UsersServiceInterface{
	
	/**	 
     * El objeto usersRepository es el que se encarga de la comunicación con la BD. 
     */
	@Autowired private UsersRepository usersRepository;
	

	/**
	  * Descripción de lo que hace la función.
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @param usersRequest Este parámetro encapsula la información solicitada por el usuario.
	  *
	  * @return response Retorna la respuesta del repositorio hacia el controlador.
	  */
	@Override
	@Transactional
	public List<UserPOJO> getAll(UsersRequest usersRequest) {
		List<Tuser> users = usersRepository.findAll();
		return generateUserDtos(users);
	}
	
	/**
	  * Descripción de lo que hace la función.
	  * Genera los objetos POJOS que se retornaran a la UI.
	  *
	  * @param users Lista de usuarios.
	  *
	  * @return uiUsers Se retorna una lista de usuarios POJOS.
	  */
	private List<UserPOJO> generateUserDtos(List<Tuser> users) {
		List<UserPOJO> uiUsers = new ArrayList<UserPOJO>();
		users.stream().forEach(u -> {
			UserPOJO dto = new UserPOJO();
			BeanUtils.copyProperties(u, dto);
			uiUsers.add(dto);
		});
		return uiUsers;
	}
	
	/**
	  * Descripción de lo que hace la función.
	  * Este método registra un usuario en el sistema.
	  *
	  * @param userRequest Encapsula los datos requeridos por el usuario.
      * 
	  * @return nuser Retorna el usuario creado.
	  */
	@Override
	public Tuser saveUser(UsersRequest userRequest) {
		
		Tuser user = new Tuser();
		BeanUtils.copyProperties(userRequest.getUser(), user);
		//Combertir el pass a md5
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			user.setPassword(md.digest(user.getPassword().getBytes()).toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Tuser nuser = usersRepository.save(user);
		
		return nuser;
	
	}

}
