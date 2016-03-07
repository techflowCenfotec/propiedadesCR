package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tproperty;

/**
* <h1>POJO de los beneficios de la propiedad</h1>
* Clase que contiene los atributos de los beneficios.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class BenefitsPOJO {

	/**
     * Id del beneficio.
     */	
	private int idBenefit;
	/**
     * Nombre del beneficio.
     */	
	private String benefit;
	/**
     * Lista de propiedades a las que pertenece.
     */	
	private List<Tproperty> tproperties;
	
	public BenefitsPOJO() {
		super();
	}
	public int getIdBenefit() {
		return idBenefit;
	}
	public void setIdBenefit(int idBenefit) {
		this.idBenefit = idBenefit;
	}
	public String getBenefit() {
		return benefit;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public List<Tproperty> getTproperties() {
		return tproperties;
	}
	public void setTproperties(List<Tproperty> tproperties) {
		this.tproperties = tproperties;
	}
	
}