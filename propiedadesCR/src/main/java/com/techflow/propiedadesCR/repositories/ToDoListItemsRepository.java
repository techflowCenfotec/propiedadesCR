package com.techflow.propiedadesCR.repositories;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Titem;

public interface ToDoListItemsRepository extends CrudRepository<Titem, Integer> {

}
