package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tquestion;

public interface QuestionsRepository extends CrudRepository<Tquestion, Integer>{

	List<Tquestion> findAll();
	
}
