package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.TpropertyReview;
import com.techflow.propiedadesCR.ejb.Tuser;

public interface ReviewRepository extends CrudRepository<TpropertyReview, Integer>{
	
	/**
	  * Retorna una lista de entidades.
	  * 
	  * @return List<TpropertyRating> Las entidades del tipo
	  */
	List<TpropertyReview> findAll();
	TpropertyReview findByTuserAndTproperty(Tuser client, Tproperty property);
}
