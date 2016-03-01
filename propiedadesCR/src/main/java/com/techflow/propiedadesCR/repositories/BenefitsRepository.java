package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tbenefit;
/**
* <h1>BenefitsRepository</h1>
* Repository that extends from CrudRepository
* and provides one implementation
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface BenefitsRepository extends CrudRepository<Tbenefit, Integer> {

	/**
	  * Returns all instances of the Tbenefit ejb
	  * 
	  * @return All entities of the Tbenefit ejbs
	  */
	List<Tbenefit> findAll();
	
	List<Tbenefit> findByIdBenefitIn(int[] benefits);
}
