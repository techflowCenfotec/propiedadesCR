package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.BenefitsRequest;
import com.techflow.propiedadesCR.ejb.Tbenefit;
import com.techflow.propiedadesCR.pojo.BenefitsPOJO;

/**
* <h1>BenefitsServiceInterface</h1>
* Interface for all benefits operations.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface BenefitsServiceInterface {

	/**
	  * Returns all benefits of the type BenefitsPOJO
	  *  
	  * @param br - must not be null.
	  * @return all entities of the type.
	  */
	List<BenefitsPOJO> getAll();
	
	/**
	  * Returns one benefit entity by it's id.
	  *  
	  * @param idBenefit - must not be null.
	  * @return one entity of the type benefit.
	  */
	Tbenefit getBenefitById(int idBenefit);
	
	/**
	  * Returns a list of all benefits entities by it's id.
	  *  
	  * @param idBenefit - must not be null.
	  * @return a list of entities of the type benefit.
	  */
	List<Tbenefit> getBenefitsById(int idBenefit);

	List<Tbenefit> getBenefits(int[] benefits);
}
