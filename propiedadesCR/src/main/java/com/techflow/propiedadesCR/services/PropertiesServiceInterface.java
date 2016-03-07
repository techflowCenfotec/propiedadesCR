package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;

/**
* <h1>Interfaz a implementar por el PropertiesService</h1>
* Interfaz para las operaciones de las propiedades.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertiesServiceInterface {
	/**
	  * Retorna todas las entidades del tipo.
	  *  
	  * @return todas las entidades del tipo.
	  */
	List<PropertyPOJO> getAll();

	/**
	  * Retorna una sola entidad por su id.
	  *  
	  * @param pIdProperty - no debe ser nulo.
	  * @return una entidad del tipo.
	  */
	Tproperty getPropertyById(int pIdProperty);

	/**
	 * Guarda la información a almacenar de la propiedad.
	 *  
	 * @param pProperty - ejb con la información a almacenar. No debe ser nulo.
	 * @return un objeto respuesta de la propiedad.
	 */
	Tproperty saveProperty(Tproperty pProperty);
}
