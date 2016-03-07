package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.DistrictRequest;
import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;

/**
* <h1>CountiesServiceInterface</h1>
* Interface for all districts operations.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface DistrictServiceInterface {
	/**
	  * Returns all districts of the type DistrictsPOJO
	  *  
	  * @param dr - must not be null.
	  * @return all entities of the type.
	  */
	List<DistrictPOJO> getAll(DistrictRequest dr);
	
	/**
	  * Returns one county entity by it's id.
	  *  
	  * @param idDistrict - must not be null.
	  * @return one entity of the type county.
	  */
	Tdistrict getDistrictById(int idDistrict);
}
