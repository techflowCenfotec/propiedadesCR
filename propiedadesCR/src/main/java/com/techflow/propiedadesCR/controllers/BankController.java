package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.BankRequest;
import com.techflow.propiedadesCR.contracts.BankResponse;
import com.techflow.propiedadesCR.services.BankServiceInterface;

@RestController
@RequestMapping(value = "rest/protected/banks")
public class BankController {
	
	@Autowired private BankServiceInterface bankService;
	
	@RequestMapping(value="/getAll",method = RequestMethod.POST)
	public BankResponse getAll(@RequestBody BankRequest pbankRequest){
		BankResponse response = new BankResponse();
		response.setBanks(bankService.getAll());
		response.setCode(200);
		response.setCodeMessage("fetch successful");
		
		return response;
	}

}
