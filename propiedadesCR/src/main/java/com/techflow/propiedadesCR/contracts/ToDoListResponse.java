package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.ToDoListPOJO;

public class ToDoListResponse extends BaseResponse{

	private List<ToDoListPOJO> toDoList;

	public List<ToDoListPOJO> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoListPOJO> toDoList) {
		this.toDoList = toDoList;
	}
	
	
}
