package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.ToDoListRequest;
import com.techflow.propiedadesCR.contracts.ToDoListResponse;
import com.techflow.propiedadesCR.ejb.TToDoList;
import com.techflow.propiedadesCR.services.ToDoListServiceInterface;

@RestController
@RequestMapping(value= "rest/protected/todolist")
public class ToDoListController {
	
	@Autowired private ToDoListServiceInterface toDoListService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public ToDoListResponse getAll(@RequestBody ToDoListRequest ptoDoListRequest){
		ToDoListResponse response = new ToDoListResponse();
		response.setCode(200);
		response.setCodeMessage("ToDoList fetch successful");
		response.setToDoList(toDoListService.getAll(ptoDoListRequest));
		return response;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ToDoListResponse createToDoList(@RequestBody ToDoListRequest ptoDoListRequest){
		ToDoListResponse response = new ToDoListResponse();
		boolean idUser = false;
		
		if(ptoDoListRequest.getToDoList().getTuser()!=null){
			idUser=true;
		}
		
		if(idUser){
			TToDoList toDoList = toDoListService.saveToDoList(ptoDoListRequest);
			if(toDoList != null){
				response.setCode(200);
				response.setCodeMessage("to-do list created successfully");
			}
		}
		return response;
	}

}
