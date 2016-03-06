package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.ejb.Tcounty;
import com.techflow.propiedadesCR.ejb.Tprovince;
import com.techflow.propiedadesCR.pojo.CountyPOJO;
import com.techflow.propiedadesCR.pojo.ProvincePOJO;
import com.techflow.propiedadesCR.repositories.CountiesRepository;

@Service
public class CountiesService implements CountiesServiceInterface {

	@Autowired private CountiesRepository countiesRepository;
	@Autowired private ProvinceServiceInterface provinceService;
	
	@Override
	@Transactional
	public List<CountyPOJO> getAll() {
		List<Tcounty> counties = countiesRepository.findAll();
		return generateCountyDtos(counties);
	}
	
	private List<CountyPOJO> generateCountyDtos(List<Tcounty> counties) {
		List<CountyPOJO> uiCounties = new ArrayList<CountyPOJO>();
		counties.stream().forEach(u -> {
			CountyPOJO dto = new CountyPOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTdistricts(null);
			dto.setTprovince(null);
			uiCounties.add(dto);
		});
		return uiCounties;
	}

	@Override
	public Tcounty getCountyById(int idCounty) {
		return countiesRepository.findOne(idCounty);
	}

}
