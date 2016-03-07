/**
* <h1>Repositorio de login</h1>
* 
* Esta clase se encarga de la comunicación con la BD
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 3/5/2016
*/
package com.techflow.propiedadesCR.repositories;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tuser;


public interface LoginRepository extends CrudRepository<Tuser, Integer>{
	
	/**
	  * Este método retorna el usuario que esta iniciando sesión a la aplicación
	  *
	  * @return Tuser Retorna el usuario si este esta registrado en la BD 
	  */
	Tuser findByEmailAndPassword(String pemail, String ppassword);
}
