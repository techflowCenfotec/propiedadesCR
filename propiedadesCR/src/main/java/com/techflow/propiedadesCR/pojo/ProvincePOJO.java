package com.techflow.propiedadesCR.pojo;

import java.util.List;

/**
* <h1>POJO de la provincia de la propiedad</h1>
* Clase que contiene los atributos de los beneficios.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class ProvincePOJO {

	/**
     * Id de la provincia.
     */	
	private int idProvince;
	/**
     * Código de área de la provincia.
     */	
	private String code;
	/**
     * Nombre de la provincia.
     */	
	private String name;
	/**
     * Cantones que posee.
     */	
	private List<CountyPOJO> tcounties;
	/**
     * Propiedades dentro de la propvincia.
     */	
	private List<PropertyPOJO> tproperties;
	
	public ProvincePOJO() {
		super();
	}

	public int getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(int idProvince) {
		this.idProvince = idProvince;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CountyPOJO> getTcounties() {
		return tcounties;
	}

	public void setTcounties(List<CountyPOJO> tcounties) {
		this.tcounties = tcounties;
	}

	public List<PropertyPOJO> getTproperties() {
		return tproperties;
	}

	public void setTproperties(List<PropertyPOJO> tproperties) {
		this.tproperties = tproperties;
	}
}
