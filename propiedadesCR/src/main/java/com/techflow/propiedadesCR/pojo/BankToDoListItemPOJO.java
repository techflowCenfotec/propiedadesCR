package com.techflow.propiedadesCR.pojo;

import com.techflow.propiedadesCR.ejb.TbankToDoList;

public class BankToDoListItemPOJO {
	
	/**
     * Id del item del to-do list del banco
     */
	private int idtBank_item;
	
	/**
     * Nombre del item del to-do list del banco
     */
	private String name;
	
	/**
     * To-do list del banco al que pertence el item
     */
	private TbankToDoList tbankToDoList;
	
	public BankToDoListItemPOJO(){
		super();
	}
	
	public int getIdtBank_item() {
		return idtBank_item;
	}

	public void setIdtBank_item(int idtBank_item) {
		this.idtBank_item = idtBank_item;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TbankToDoList getTbankToDoList() {
		return tbankToDoList;
	}
	public void setTbankToDoList(TbankToDoList tbankToDoList) {
		this.tbankToDoList = tbankToDoList;
	}


}
