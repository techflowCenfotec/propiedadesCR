package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;

/**
* <h1>PropertiesServiceInterface</h1>
* Interface for all properties operations.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertiesServiceInterface {
	/**
	 * Returns all properties of the type PropertyPOJO
	 *  
	 * @param pr - must not be null.
	 * @return all entities of the type.
	 */
	List<PropertyPOJO> getAll();

	/**
	 * Returns one property entity by it's id.
	 *  
	 * @param idProperty - must not be null.
	 * @return one entity of the type property.
	 */
	Tproperty getPropertyById(int idProperty);

	/**
	 * Request all properties information through the service.
	 *  
	 * @param pr Parameter for the getAll method of the service
	 * @return a property response object.
	 */
	Tproperty saveProperty(Tproperty pr);
}
