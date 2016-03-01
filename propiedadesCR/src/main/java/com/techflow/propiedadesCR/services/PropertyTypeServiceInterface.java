package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.PropertyTypeRequest;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;

/**
* <h1>PropertyTypeServiceInterface</h1>
* Interface for all property types operations.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertyTypeServiceInterface {
	
	/**
	  * Returns all properties of the type PropertyPOJO
	  *  
	  * @param ptr - must not be null.
	  * @return all entities of the type.
	  */
	List<PropertyTypePOJO> getAll();
	
	/**
	  * Returns one property entity by it's id.
	  *  
	  * @param idProperty - must not be null.
	  * @return one entity of the type property.
	  */
	TpropertyType getPropertyTypeById(int idPropertyType);
}
