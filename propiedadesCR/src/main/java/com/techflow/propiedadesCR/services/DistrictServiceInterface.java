package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;

public interface DistrictServiceInterface {

	List<DistrictPOJO> getAll();
	DistrictPOJO getDistrictById(int pIdDistrict);
}
