package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.techflow.propiedadesCR.ejb.Trole;

public interface RoleRepository extends CrudRepository<Trole, Integer>{
	List<Trole> findAll();
	//List<Trole> findByNameContaining(String name);
}
