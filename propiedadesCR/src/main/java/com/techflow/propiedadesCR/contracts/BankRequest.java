package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.BankPOJO;

public class BankRequest extends BaseRequest {
	
	private BankPOJO bank;
	
	public BankRequest(){
		super();
	}

	public BankPOJO getBank() {
		return bank;
	}

	public void setBank(BankPOJO bank) {
		this.bank = bank;
	}

}
