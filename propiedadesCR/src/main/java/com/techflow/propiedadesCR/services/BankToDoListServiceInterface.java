package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.BankToDoListRequest;
import com.techflow.propiedadesCR.ejb.TbankToDoList;
import com.techflow.propiedadesCR.pojo.BankToDoListPOJO;

public interface BankToDoListServiceInterface {
	List<BankToDoListPOJO> getAll(BankToDoListRequest pbankToDoListRequest);

	TbankToDoList saveBankToDoList(BankToDoListRequest pbankToDoListRequest);
}
