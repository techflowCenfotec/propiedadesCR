package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TToDoList;

public interface ToDoListRepository extends CrudRepository<TToDoList,Integer>{
	List<TToDoList> findAll();
}
