package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.BankToDoListPOJO;

public class BankToDoListRequest extends BaseRequest{

	private BankToDoListPOJO bankToDoList;
	
	public BankToDoListRequest(){
		super();
	}

	public BankToDoListPOJO getBankToDoList() {
		return bankToDoList;
	}

	public void setBankToDoList(BankToDoListPOJO bankToDoList) {
		this.bankToDoList = bankToDoList;
	}
	
	
}
