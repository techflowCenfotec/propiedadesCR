package com.techflow.propiedadesCR.pojo;

public class ToDoListPOJO {
	private int idToDoList;
	private String bank;
	private String description;
	private String name;
	//private Tuser tuser;
	//private List<Titem> titems;
	
	public ToDoListPOJO(){
		super();
	}
	
	public int getIdToDoList() {
		return idToDoList;
	}
	public void setIdToDoList(int idToDoList) {
		this.idToDoList = idToDoList;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
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
	
	
}
