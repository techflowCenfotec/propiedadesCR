package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.BankPOJO;

public class BankResponse extends BaseResponse {
	
	private List<BankPOJO> banks;

	public BankResponse() {
		super();
	}

	public List<BankPOJO> getBanks() {
		return banks;
	}

	public void setBanks(List<BankPOJO> banks) {
		this.banks = banks;
	}
	
}
