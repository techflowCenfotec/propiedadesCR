package com.techflow.propiedadesCR.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.techflow.propiedadesCR.ejb.Tuser;

public interface UsersRepository extends CrudRepository<Tuser, Integer> {
	List<Tuser> findAll();
	
}


