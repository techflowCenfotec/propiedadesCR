package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.ToDoListRequest;
import com.techflow.propiedadesCR.ejb.TToDoList;
import com.techflow.propiedadesCR.pojo.ToDoListPOJO;

public interface ToDoListServiceInterface {
	List<ToDoListPOJO> getAll(ToDoListRequest ptoDoListRequest);

	TToDoList saveToDoList(ToDoListRequest ptoDoListRequest);
}
