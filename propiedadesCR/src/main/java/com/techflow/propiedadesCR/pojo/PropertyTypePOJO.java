package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.Tresidence;

/**
* <h1>propertyTypePOJO</h1>
* Plain old Java object clas that provides the attributes and methods
* necessary to implement it.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class PropertyTypePOJO {

	private int idPropertyType;
	private String name;
	private List<Tproperty> tproperties;
	private List<Tresidence> tresidences;
	
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
	public List<Tproperty> getTproperties() {
		return tproperties;
	}
	public void setTproperties(List<Tproperty> tproperties) {
		this.tproperties = tproperties;
	}
	public List<Tresidence> getTresidences() {
		return tresidences;
	}
	public void setTresidences(List<Tresidence> tresidences) {
		this.tresidences = tresidences;
	}
}
