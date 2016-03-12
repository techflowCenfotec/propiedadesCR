package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tdistrict;

/**
* <h1>Repositorio de los Distritos</h1>
* Repositorio que extiende de CrudRepository
* y provee una implementación
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public interface DistrictRepository extends CrudRepository<Tdistrict, Integer> {

	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return List<Tdistrict> Las entidades del tipo
	  */
	List<Tdistrict> findAll();
}
