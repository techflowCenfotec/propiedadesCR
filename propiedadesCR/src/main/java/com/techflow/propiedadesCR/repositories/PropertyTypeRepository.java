package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TpropertyType;

/**
* <h1>PropertyTypeRepository</h1>
* Repository that extends from CrudRepository
* and provides one implementation
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertyTypeRepository extends CrudRepository<TpropertyType, Integer> {

	/**
	  * Returns all instances of the TpropertyType ejb
	  * 
	  * @return All entities of the TpropertyType ejbs
	  */
	List<TpropertyType> findAll();
}
