package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.BenefitsPOJO;

public class BenefitsResponse extends BaseResponse {

	private List<BenefitsPOJO> benefits;
	
	public BenefitsResponse() {
		super();
	}

	public List<BenefitsPOJO> getBenefits() {
		return benefits;
	}

	public void setBenefits(List<BenefitsPOJO> benefits) {
		this.benefits = benefits;
	}
	
	
}
