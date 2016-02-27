package com.techflow.propiedadesCR.pojo;

import java.util.List;

public class ToDoListPOJO {
	private int idToDoList;
	private String description;
	private String name;
	private UserPOJO tuser;
	private List<ToDoListItemPOJO> titems;
	
	public ToDoListPOJO(){
		super();
	}
	
	public int getIdToDoList() {
		return idToDoList;
	}
	public void setIdToDoList(int idToDoList) {
		this.idToDoList = idToDoList;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public UserPOJO getTuser() {
		return tuser;
	}

	public void setTuser(UserPOJO tuser) {
		this.tuser = tuser;
	}

	public List<ToDoListItemPOJO> getTitems() {
		return titems;
	}

	public void setTitems(List<ToDoListItemPOJO> titems) {
		this.titems = titems;
	}
	
	
	
}
