package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.CountiesRequest;
import com.techflow.propiedadesCR.ejb.Tcounty;
import com.techflow.propiedadesCR.pojo.CountyPOJO;

public interface CountiesServiceInterface {

	List<CountyPOJO> getAll();
	Tcounty getCountyById(int pIdCounty);
}
