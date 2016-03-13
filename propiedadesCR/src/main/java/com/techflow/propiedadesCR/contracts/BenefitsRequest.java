package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.BenefitsPOJO;

public class BenefitsRequest extends BaseRequest {

	BenefitsPOJO benefit;
	
	public BenefitsRequest() {
		super();
	}

	public BenefitsPOJO getBenefit() {
		return benefit;
	}

	public void setBenefit(BenefitsPOJO benefit) {
		this.benefit = benefit;
	}
	
	@Override
	public String toString() {
		return "BenefitRequest [benefit= " + benefit + "]";
	}
}
