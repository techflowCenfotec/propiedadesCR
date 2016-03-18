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
import org.springframework.data.repository.CrudRepository;
import com.techflow.propiedadesCR.ejb.Tuser;

public interface UsersRepository extends CrudRepository<Tuser, Integer> {
	/**
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @return List<Tuser> Retorna la respuesta de la BD hacia el servicio. 
	  */
	List<Tuser> findAll();
	/**
	  * Este método el usuario con el identificador a buscar
	  *
	  * @return Tuser Retorna la respuesta de la BD hacia el servicio. 
	  */
	Tuser findByIdUser(int pidUser);
	

}


