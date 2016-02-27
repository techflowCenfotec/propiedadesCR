package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.ejb.Tevent;

public interface EventsRepository extends CrudRepository<Tevent, Integer>{
	
	List<Tevent> findAll();
	
}

