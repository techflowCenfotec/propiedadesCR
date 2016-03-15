package com.techflow.propiedadesCR.pojo;
/**
* <h1>POJO del banco</h1>
* 
* Enfatiza el uso de la clase simple "banco"
* 
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/
public class BankPOJO {
	
	/**
     * Id del banco
     */
	private int idBank;
	
	/**
     * Nombre del Banco
     */
	private String name;
	
	public BankPOJO(){
		super();
	}

	public int getIdBank() {
		return idBank;
	}

	public void setIdBank(int idBank) {
		this.idBank = idBank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
