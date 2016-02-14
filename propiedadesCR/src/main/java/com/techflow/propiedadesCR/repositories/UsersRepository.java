package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.User;

public interface UsersRepository extends CrudRepository<User, Integer> {

	User findByEmailAndPassword(String email,String password);
	List<User> findAll();
	List<User> findByFirstnameContaining(String name);
}
