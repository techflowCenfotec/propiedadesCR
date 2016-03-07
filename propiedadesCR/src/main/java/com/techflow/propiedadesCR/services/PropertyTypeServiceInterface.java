package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.PropertyTypeRequest;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.pojo.PropertyTypePOJO;

/**
* <h1>Interfaz a implementar por el PropertyTypeService</h1>
* Interfaz para las operaciones de los tipos de propiedad.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertyTypeServiceInterface {
	
	/**
	  * Retorna todas las entidades del tipo.
	  *  
	  * @return List<PropertyTypePOJO> Todas las entidades del tipo.
	  */
	List<PropertyTypePOJO> getAll();
	
	/**
	  * Retorna una sola entidad por su id.
	  *  
	  * @param pIdPropertyType Id de la propiedad. No debe ser nulo.
	  * @return TpropertyType Una entidad del tipo.
	  */
	TpropertyType getPropertyTypeById(int pIdPropertyType);
}
