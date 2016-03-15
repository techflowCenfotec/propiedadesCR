package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tcounty;

/**
* <h1>Repositorio de los Cantones</h1>
* Repositorio que extiende de CrudRepository
* y provee una implementaci—n
*
* @author  Walter G—mez
* @version 1.0
* @since 26/2/2016
*/
public interface CountiesRepository extends CrudRepository<Tcounty, Integer> {

	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return List<Tcounty> Las entidades del tipo
	  */
	List<Tcounty> findAll();
}
