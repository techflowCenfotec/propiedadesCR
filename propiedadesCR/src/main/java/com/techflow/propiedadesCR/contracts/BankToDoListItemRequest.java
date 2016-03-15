package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.BankToDoListItemPOJO;

public class BankToDoListItemRequest extends BaseRequest{
	private BankToDoListItemPOJO bankToDoListItem;
	
	public BankToDoListItemRequest(){
		super();
	}

	public BankToDoListItemPOJO getBankToDoListItem() {
		return bankToDoListItem;
	}

	public void setBankToDoListItem(BankToDoListItemPOJO bankToDoListItem) {
		this.bankToDoListItem = bankToDoListItem;
	}
	
	
}
