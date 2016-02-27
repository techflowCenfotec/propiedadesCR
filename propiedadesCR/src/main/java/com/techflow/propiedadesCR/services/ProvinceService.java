package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.ProvinceRequest;
import com.techflow.propiedadesCR.ejb.Tprovince;
import com.techflow.propiedadesCR.pojo.ProvincePOJO;
import com.techflow.propiedadesCR.repositories.ProvinceRepository;

/**
* <h1>ProvinceService</h1>
* Service that provides the implementation of the interface
* contains getAll() method and getProvinceById().
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class ProvinceService implements ProvinceServiceInterface {

	@Autowired private ProvinceRepository provinceRepository;
	
	/**
	  * Method that returns a list of data transfer objects (DTOs)
	  * @param N/A
	  * @return dtos This returns a list of ProvincePOJO as dtos
	  */
	@Override
	@Transactional
	public List<ProvincePOJO> getAll(ProvinceRequest pr) {
		List<Tprovince> provinces = provinceRepository.findAll();
		return generateProvinceDtos(provinces);
	}
	
	private List<ProvincePOJO> generateProvinceDtos(List<Tprovince> provinces) {
		List<ProvincePOJO> uiProvinces = new ArrayList<ProvincePOJO>();
		
		provinces.stream().forEach(u ->{
			ProvincePOJO dto = new ProvincePOJO();
			BeanUtils.copyProperties(u, dto);
			uiProvinces.add(dto);
		});
		return uiProvinces;
	}

	/**
	  * Method that retrieves a repository based on Id sent
	  * @param idProvince must not be null
	  * @return te repository entity or null if none is found
	  */
	@Override
	public Tprovince getProvinceById(int idProvince) {
		return provinceRepository.findOne(idProvince);
	}

}
