package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tprovince;
import com.techflow.propiedadesCR.pojo.ProvincePOJO;

public interface ProvinceServiceInterface {
	
	List<ProvincePOJO> getAll();
	Tprovince getProvinceById(int pIdProvince);
}
