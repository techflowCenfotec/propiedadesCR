package com.techflow.propiedadesCR.pojo;

import java.util.List;

/**
* <h1>POJO de los cantones de la propiedad</h1>
* Clase que contiene los atributos de los cantones.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class CountyPOJO {

	/**
     * Id del cantón.
     */	
	private int idCounty;
	/**
     * Código de rea del cantón.
     */	
	private int code;
	/**
     * Nombre del cantón.
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
		this.tprovince =  new ProvincePOJO();
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
