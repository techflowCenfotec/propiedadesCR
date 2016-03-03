package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.techflow.propiedadesCR.ejb.Tpermission;

public interface PermissionsRepository extends CrudRepository<Tpermission, Integer>{
	List<Tpermission> findAll();
}
