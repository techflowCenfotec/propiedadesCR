package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tprovince;

/**
* <h1>ProvinceRepository</h1>
* Java repository that extends from CrudRepository
* and provides one implementation
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface ProvinceRepository extends CrudRepository<Tprovince, Integer> {
	
	/**
	  * Returns all instances of the Tprovince ejb
	  * 
	  * @return All entities of the Tprovince ejbs
	  */
	List<Tprovince> findAll();
}
