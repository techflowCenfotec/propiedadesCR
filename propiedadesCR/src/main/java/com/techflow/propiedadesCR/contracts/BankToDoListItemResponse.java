package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.BankToDoListItemPOJO;

public class BankToDoListItemResponse extends BaseResponse{
	
	private List<BankToDoListItemPOJO> BankToDoListItems;

	public BankToDoListItemResponse(){
		super();
	}

	public List<BankToDoListItemPOJO> getBankToDoListItems() {
		return BankToDoListItems;
	}

	public void setBankToDoListItems(List<BankToDoListItemPOJO> bankToDoListItems) {
		BankToDoListItems = bankToDoListItems;
	}
	
	
}
