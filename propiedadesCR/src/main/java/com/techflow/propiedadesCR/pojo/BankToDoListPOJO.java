package com.techflow.propiedadesCR.pojo;

import java.util.List;
/**
* <h1>POJO del to-do list del banco</h1>
* 
* Enfatiza el uso de la clase simple "to-do list banco"
* 
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/
public class BankToDoListPOJO {
    
	/**
     * Id del to-do list del banco
     */	
	private int idtBank_to_do_list;
    
	/**
     * Descripcion del to-do list del banco
     */
	private String description;
	
    /**
     * Nombre del to-do list del banco
     */
	private String name;
    
	/**
     * Banco al que pertenece el to-do list
     */
	private BankPOJO tbank;
	
    /**
     * Lista de items del to-do list
     */
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
