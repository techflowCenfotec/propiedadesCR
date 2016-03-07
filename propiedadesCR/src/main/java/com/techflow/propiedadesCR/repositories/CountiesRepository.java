package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tcounty;

/**
* <h1>CountiesRepository</h1>
* Repository that extends from CrudRepository
* and provides one implementation
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface CountiesRepository extends CrudRepository<Tcounty, Integer> {

	/**
	  * Returns all instances of the Tcounty ejb
	  * 
	  * @return All entities of the Tcounty ejbs
	  */
	List<Tcounty> findAll();
}
