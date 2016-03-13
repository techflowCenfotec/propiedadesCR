package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TpropertyImage;

/**
* <h1>Repositorio de las imagenes de la propiedad</h1>
* Repositorio que extiende de CrudRepository
* y provee una implementación
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface PropertyImageRepository extends CrudRepository<TpropertyImage, Integer> {

	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return List<TpropertyImage> Las entidades del tipo
	  */
	List<TpropertyImage> findAll();
	
}
