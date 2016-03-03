package com.techflow.propiedadesCR.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.techflow.propiedadesCR.ejb.Tuser;
/**
* <h1>UsersRepository</h1>
* 
* Descripción de la clase.
* Se encarga de la comunicación con la BD
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 25/2/2016
*/
public interface UsersRepository extends CrudRepository<Tuser, Integer> {
	/**
	  * Descripción de lo que hace la función.
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @return List<Tuser> Retorna la respuesta de la BD hacia el servicio. 
	  */
	List<Tuser> findAll();
	
}


