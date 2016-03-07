package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tprovince;

/**
* <h1>Repositorio de las provincias</h1>
* Repositorio que extiende de CrudRepository
* y provee una implementación
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface ProvinceRepository extends CrudRepository<Tprovince, Integer> {
	
	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return List<Tprovince> - las entidades del tipo
	  */
	List<Tprovince> findAll();
}
