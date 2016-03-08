package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.BenefitsRequest;
import com.techflow.propiedadesCR.ejb.Tbenefit;
import com.techflow.propiedadesCR.pojo.BenefitsPOJO;

public interface BenefitsServiceInterface {

	List<BenefitsPOJO> getAll();
	
	Tbenefit getBenefitById(int pIdBenefit);
	
	List<Tbenefit> getBenefits(int[] pBenefits);
}
