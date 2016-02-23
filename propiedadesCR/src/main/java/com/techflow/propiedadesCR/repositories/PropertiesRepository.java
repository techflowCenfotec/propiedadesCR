package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tproperty;

public interface PropertiesRepository extends CrudRepository<Tproperty, Integer>{
	
	List<Tproperty> findAll();
}
