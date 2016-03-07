package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tdistrict;

public interface DistrictRepository extends CrudRepository<Tdistrict, Integer> {

	List<Tdistrict> findAll();
}
