package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.DistrictRequest;
import com.techflow.propiedadesCR.ejb.Tdistrict;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;
import com.techflow.propiedadesCR.repositories.DistrictRepository;

/**
* <h1>DistrictService</h1>
* Service that provides the implementation of the interface
* contains getAll() method and getDistrictById().
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class DistrictService implements DistrictServiceInterface {

	@Autowired private DistrictRepository districtRepository;
	
	/**
	  * Method that returns a list of data transfer objects (DTOs)
	  * @param N/A
	  * @return dtos This returns a list of DistrictPOJO as dtos
	  */
	@Override
	@Transactional
	public List<DistrictPOJO> getAll(DistrictRequest dr) {
		List<Tdistrict> districts = districtRepository.findAll();
		return generateDistrictDtos(districts);
	}
	
	private List<DistrictPOJO> generateDistrictDtos(List<Tdistrict> districts) {
		List<DistrictPOJO> uiDistricts = new ArrayList<DistrictPOJO>();
		districts.stream().forEach(u -> {
			DistrictPOJO dto = new DistrictPOJO();
			BeanUtils.copyProperties(u, dto);
			dto.setTcounty(null);
			uiDistricts.add(dto);
		});
		return uiDistricts;
	}

	/**
	  * Method that retrieves a repository based on Id sent
	  * @param idDistrict must not be null
	  * @return the repository entity or null if none is found
	  */
	@Override
	public Tdistrict getDistrictById(int idDistrict) {
		return districtRepository.findOne(idDistrict);
	}

}
