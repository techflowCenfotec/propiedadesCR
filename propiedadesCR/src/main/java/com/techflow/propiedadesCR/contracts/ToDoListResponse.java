package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.ToDoListPOJO;

public class ToDoListResponse extends BaseResponse{

	private List<ToDoListPOJO> toDoList;
	
	private ToDoListPOJO myTodoList;

	public List<ToDoListPOJO> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoListPOJO> toDoList) {
		this.toDoList = toDoList;
	}

	public ToDoListPOJO getMyTodoList() {
		return myTodoList;
	}

	public void setMyTodoList(ToDoListPOJO myTodoList) {
		this.myTodoList = myTodoList;
	}
	
	
}
