package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.BankToDoListRequest;
import com.techflow.propiedadesCR.contracts.BankToDoListResponse;
import com.techflow.propiedadesCR.ejb.TbankToDoList;
import com.techflow.propiedadesCR.services.BankToDoListServiceInterface;

@RestController
@RequestMapping(value = "rest/protected/banktodolist")
public class BankToDoListController {

	@Autowired private BankToDoListServiceInterface bankToDoListService;
	
	@RequestMapping(value="/getAll",method = RequestMethod.POST)
	public BankToDoListResponse getAll(@RequestBody BankToDoListRequest pbankToDoListRequest){
		BankToDoListResponse response = new BankToDoListResponse();
		response.setCode(200);
        response.setCodeMessage("banktodolist fetch successful");
        response.setBankToDoList(bankToDoListService.getAll(pbankToDoListRequest));
        return response;
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public BankToDoListResponse create(@RequestBody BankToDoListRequest pbankToDoListRequest){
		BankToDoListResponse response = new BankToDoListResponse();
		TbankToDoList bankToDoList = bankToDoListService.saveBankToDoList(pbankToDoListRequest);
		if(bankToDoList!=null){
			response.setCode(200);
			response.setCodeMessage("created successfully");
		}
		return response;
	}
}
