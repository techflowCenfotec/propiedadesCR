package com.techflow.propiedadesCR.pojo;

import java.util.List;

public class BankToDoListPOJO {
	private int idtBank_to_do_list;
	private String description;
	private String name;
	private BankPOJO tbank;
	private List<BankToDoListItemPOJO> tbankItems;

	
	public BankToDoListPOJO(){
		super();
	}
	
	public int getIdtBank_to_do_list() {
		return idtBank_to_do_list;
	}
	public void setIdtBank_to_do_list(int idtBank_to_do_list) {
		this.idtBank_to_do_list = idtBank_to_do_list;
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

	public BankPOJO getTbank() {
		return tbank;
	}

	public void setTbank(BankPOJO tbank) {
		this.tbank = tbank;
	}

	public List<BankToDoListItemPOJO> getTbankItems() {
		return tbankItems;
	}

	public void setTbankItems(List<BankToDoListItemPOJO> tbankItems) {
		this.tbankItems = tbankItems;
	}
	
	
}
