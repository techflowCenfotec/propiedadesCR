package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TbankToDoList;

public interface BankToDoListRepository extends CrudRepository<TbankToDoList, Integer>{
	List<TbankToDoList> findAll();
}
