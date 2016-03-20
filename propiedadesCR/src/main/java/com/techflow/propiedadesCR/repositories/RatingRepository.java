package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TpropertyRating;

public interface RatingRepository extends CrudRepository<TpropertyRating, Integer>{
	
	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return List<TpropertyRating> Las entidades del tipo
	  */
	List<TpropertyRating> findAll();
}
