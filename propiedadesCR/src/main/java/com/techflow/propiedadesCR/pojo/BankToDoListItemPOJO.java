package com.techflow.propiedadesCR.pojo;

import com.techflow.propiedadesCR.ejb.TbankToDoList;

public class BankToDoListItemPOJO {
	
	private int idtBank_iitem;
	private String name;
	private TbankToDoList tbankToDoList;
	
	public BankToDoListItemPOJO(){
		super();
	}
	
	public int getIdtBank_iitem() {
		return idtBank_iitem;
	}
	public void setIdtBank_iitem(int idtBank_iitem) {
		this.idtBank_iitem = idtBank_iitem;
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
