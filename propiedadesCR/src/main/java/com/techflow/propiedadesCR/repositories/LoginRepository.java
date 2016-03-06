package com.techflow.propiedadesCR.repositories;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tuser;


public interface LoginRepository extends CrudRepository<Tuser, Integer>{
	
	Tuser findByEmailAndPassword(String pemail, String ppassword);
}
