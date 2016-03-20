package com.techflow.propiedadesCR.pojo;

/**
* <h1>POJO de los distritos de la propiedad</h1>
* Clase que contiene los atributos de los distritos.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class DistrictPOJO {

	/**
     * Id del distrito.
     */	
	private int idDisctrict;
	/**
     * Código de área del distrito.
     */	
	private int code;
	/**
     * Nombre del distrito.
     */	
	private String name;
	/**
     * Cantón al que pertenece distrito.
     */	
	private CountyPOJO tcounty;
	
	public DistrictPOJO() {
		super();
		this.tcounty = new CountyPOJO();
	}
	public int getIdDisctrict() {
		return idDisctrict;
	}
	public void setIdDisctrict(int idDisctrict) {
		this.idDisctrict = idDisctrict;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CountyPOJO getTcounty() {
		return tcounty;
	}
	public void setTcounty(CountyPOJO tcounty) {
		this.tcounty = tcounty;
	}
}
