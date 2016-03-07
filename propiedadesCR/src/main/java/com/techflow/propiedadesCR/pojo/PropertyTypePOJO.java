package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tproperty;

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
}
