package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tproperty;

/**
* <h1>Repositorio de las propiedades</h1>
* Repositorio que extiende de CrudRepository
* y provee una implementación
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertiesRepository extends CrudRepository<Tproperty, Integer>{
	
	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return Page<Tproperty> Las entidades del tipo
	  */
	Page<Tproperty> findAll(Pageable pageRequest);
	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return Page<Tproperty> Las entidades del tipo
	  */
	List<Tproperty> findAll();
	
	/**
	 * Retorna una sola entidad del tipo.
	 * 
	 * @param pidProperty Id de la propiedad a buscar. No debe ser nula.
	 * @return Tproperty Retorna la entidad del tipo de la BD al servicio.
	 */
	Tproperty findByIdProperty(int pidProperty);
}
