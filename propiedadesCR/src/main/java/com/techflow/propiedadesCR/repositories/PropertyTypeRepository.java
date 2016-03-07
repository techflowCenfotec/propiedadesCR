package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TpropertyType;

/**
* <h1>Repositorio de los tipos de propiedad</h1>
* Repositorio que extiende de CrudRepository
* y provee una implementación
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertyTypeRepository extends CrudRepository<TpropertyType, Integer> {

	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return List<TpropertyType> - las entidades del tipo
	  */
	List<TpropertyType> findAll();
}
