package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tbank;

public interface BankRepository extends CrudRepository<Tbank, Integer>{
	List<Tbank> findAll();
}
