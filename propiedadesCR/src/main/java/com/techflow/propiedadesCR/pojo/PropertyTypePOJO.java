package com.techflow.propiedadesCR.pojo;

import java.util.List;

/**
* <h1>POJO de los tipos de la propiedad</h1>
* Clase que contiene los atributos de los tipos de propiedades.
*
* @author  Walter G—mez
* @version 1.0
* @since 26/2/2016
*/
public class PropertyTypePOJO {

	/**
     * Id del tipo de propiedad.
     */	
	private int idPropertyType;
	/**
     * Nombre del tipo de propiedad.
     */	
	private String name;
	/**
     * Lista de propiedades que poseen un tipo de propiedad espec’fico.
     */	
	private List<PropertyPOJO> tproperties;
	
	public PropertyTypePOJO() {
		super();
	}
	public int getIdPropertyType() {
		return idPropertyType;
	}
	public void setIdPropertyType(int idPropertyType) {
		this.idPropertyType = idPropertyType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PropertyPOJO> getTproperties() {
		return tproperties;
	}
	public void setTproperties(List<PropertyPOJO> tproperties) {
		this.tproperties = tproperties;
	}
}
