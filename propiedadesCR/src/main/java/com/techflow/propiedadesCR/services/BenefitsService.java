package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.BenefitsRequest;
import com.techflow.propiedadesCR.ejb.Tbenefit;
import com.techflow.propiedadesCR.pojo.BenefitsPOJO;
import com.techflow.propiedadesCR.repositories.BenefitsRepository;

/**
* <h1>BenefitsService</h1>
* Service that provides the implementation of the interface
* contains getAll() method and getBenefitById().
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
@Service
public class BenefitsService implements BenefitsServiceInterface {

	@Autowired private BenefitsRepository benefitsRepository;
	
	@Override
	@Transactional
	public List<BenefitsPOJO> getAll() {
		List<Tbenefit> benefits = benefitsRepository.findAll();
		return generateBenefitsDtos(benefits);
	}

	private List<BenefitsPOJO> generateBenefitsDtos(List<Tbenefit> benefits) {
		List<BenefitsPOJO> uiBenefits = new ArrayList<BenefitsPOJO>();
		benefits.stream().forEach(u -> {
			BenefitsPOJO dto = new BenefitsPOJO();
			BeanUtils.copyProperties(u, dto);
			uiBenefits.add(dto);
		});
		return uiBenefits;
	}

	@Override
	public Tbenefit getBenefitById(int idBenefit) {
		return benefitsRepository.findOne(idBenefit);
	}
	
	@Override
	@Transactional
	public List<Tbenefit> getBenefitsById(int idBenefit) {
		return null;
	}

	@Override
	public List<Tbenefit> getBenefits(int[] benefits) {
		return benefitsRepository.findByIdBenefitIn(benefits);
	}
}
