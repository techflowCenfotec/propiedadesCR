package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.ProvinceRequest;
import com.techflow.propiedadesCR.ejb.Tprovince;
import com.techflow.propiedadesCR.pojo.ProvincePOJO;

/**
* <h1>ProvinceServiceInterface</h1>
* Interface that provides the getAll() method and the getProvinceById()
* it provides a simple implementation of it's own service.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface ProvinceServiceInterface {
	/**
	  * Returns all instances of ProvincePOJO
	  * @param pr - request entity.
	  * @return All ProvincePOJO entities
	  */
	List<ProvincePOJO> getAll(ProvinceRequest pr);
	/**
	  * Returns an instances of the given type
	  * 
	  * @return A ProvincePOJO entity
	  */
	Tprovince getProvinceById(int idProvince);
}
