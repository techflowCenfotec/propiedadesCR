package com.techflow.propiedadesCR.pojo;

public class ToDoListItemPOJO {
	
	private int idItem;
	private byte done;
	private String name;
	private ToDoListPOJO TToDoList;
	
	public ToDoListItemPOJO() {
		super();
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public byte getDone() {
		return done;
	}

	public void setDone(byte done) {
		this.done = done;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ToDoListPOJO getTToDoList() {
		return TToDoList;
	}

	public void setTToDoList(ToDoListPOJO tToDoList) {
		TToDoList = tToDoList;
	}

	
}
