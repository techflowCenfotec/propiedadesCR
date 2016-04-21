/**
* <h1>Repositorio de usuarios</h1>
* 
* Esta clase se encarga de la comunicación con la BD
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 25/2/2016
*/
package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.ejb.Tuser;

public interface UsersRepository extends CrudRepository<Tuser, Integer> {
	/**
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @return List<Tuser> Retorna la respuesta de la BD hacia el servicio. 
	  */
	List<Tuser> findAll();
	/**
	  * Este método retorna el usuario con el identificador a buscar.
	  *
	  *@param pidUser Identificador del usuario a buscar.
	  *
	  * @return Tuser Retorna la respuesta de la BD hacia el servicio. 
	  */
	Tuser findByIdUser(int pidUser);
	
	/**
	  * Este método retorna todos los usuarios registrados en el sistema.
	  *
	  * @return List<Tuser> Retorna la respuesta de la BD hacia el servicio. 
	  */
	Page<Tuser> findAllByTrole(Trole role, Pageable pageRequest);
	
	/**
	  * Este método retorna el usuario con el correo a buscar.
	  *
	  *@param pemail Correo del usuario a buscar.
	  *
	  * @return Tuser Retorna la respuesta de la BD hacia el servicio. 
	  */
	Tuser findUserByEmail(String pemail);
	
	
}


