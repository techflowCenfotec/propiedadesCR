package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.CountiesRequest;
import com.techflow.propiedadesCR.ejb.Tcounty;
import com.techflow.propiedadesCR.pojo.CountyPOJO;

/**
* <h1>CountiesServiceInterface</h1>
* Interface for all counties operations.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface CountiesServiceInterface {

	/**
	  * Returns all properties of the type CountyPOJO
	  *  
	  * @param cr - must not be null.
	  * @return all entities of the type.
	  */
	List<CountyPOJO> getAll();
	
	/**
	  * Returns one county entity by it's id.
	  *  
	  * @param idCounty - must not be null.
	  * @return one entity of the type county.
	  */
	Tcounty getCountyById(int idCounty);
}
