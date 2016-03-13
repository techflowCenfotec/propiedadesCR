package com.techflow.propiedadesCR.pojo;

public class ToDoListItemPOJO {
	
    /**
     * Id del item
     */
	private int idItem;
	
	/**
     * Estado del item
     */
	private byte done;
	
	/**
     * Nombre del item
     */
	private String name;
	
	/**
     * To-do list al que pertenece el item
     */
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
