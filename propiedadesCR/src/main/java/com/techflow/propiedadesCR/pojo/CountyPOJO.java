package com.techflow.propiedadesCR.pojo;

import java.util.List;

/**
* <h1>POJO de los cantones de la propiedad</h1>
* Clase que contiene los atributos de los cantones.
*
* @author  Walter G—mez
* @version 1.0
* @since 26/2/2016
*/
public class CountyPOJO {

	/**
     * Id del cant—n.
     */	
	private int idCounty;
	/**
     * C—digo de rea del cant—n.
     */	
	private int code;
	/**
     * Nombre del cant—n.
     */	
	private String name;
	/**
     * Provincia a la que pertenece.
     */	
	private ProvincePOJO tprovince;
	/**
     * Distritos que posee.
     */	
	private List<DistrictPOJO> tdistricts;
	
	public CountyPOJO() {
		super();
	}

	public int getIdCounty() {
		return idCounty;
	}

	public void setIdCounty(int idCounty) {
		this.idCounty = idCounty;
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

	public ProvincePOJO getTprovince() {
		return tprovince;
	}

	public void setTprovince(ProvincePOJO tprovince) {
		this.tprovince = tprovince;
	}

	public List<DistrictPOJO> getTdistricts() {
		return tdistricts;
	}

	public void setTdistricts(List<DistrictPOJO> tdistricts) {
		this.tdistricts = tdistricts;
	}
}
