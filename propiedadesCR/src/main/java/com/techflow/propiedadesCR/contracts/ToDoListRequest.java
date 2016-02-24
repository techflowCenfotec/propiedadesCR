package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.ToDoListPOJO;

public class ToDoListRequest extends BaseRequest{
	ToDoListPOJO toDoList;
	
	public ToDoListRequest(){
		super();
	}

	public ToDoListPOJO getToDoList() {
		return toDoList;
	}

	public void setToDoList(ToDoListPOJO toDoList) {
		this.toDoList = toDoList;
	}
	
	
}
