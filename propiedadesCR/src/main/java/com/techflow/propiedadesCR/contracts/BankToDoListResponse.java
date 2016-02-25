package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.BankToDoListPOJO;

public class BankToDoListResponse extends BaseResponse{

	private List<BankToDoListPOJO> bankToDoList;
	
	public BankToDoListResponse(){
		super();
	}

	public List<BankToDoListPOJO> getBankToDoList() {
		return bankToDoList;
	}

	public void setBankToDoList(List<BankToDoListPOJO> bankToDoList) {
		this.bankToDoList = bankToDoList;
	}
	
	
}
