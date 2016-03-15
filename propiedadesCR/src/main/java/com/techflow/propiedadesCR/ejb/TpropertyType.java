package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tproperty_type database table.
 * 
 */
@Entity
@Table(name="tproperty_type")
@NamedQuery(name="TpropertyType.findAll", query="SELECT t FROM TpropertyType t")
public class TpropertyType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idPropertyType;
	private String name;
	private List<Tproperty> tproperties;

	public TpropertyType() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_property_type")
	public int getIdPropertyType() {
		return this.idPropertyType;
	}

	public void setIdPropertyType(int idPropertyType) {
		this.idPropertyType = idPropertyType;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Tproperty
	@OneToMany(mappedBy="tpropertyType")
	@JsonIgnore
	public List<Tproperty> getTproperties() {
		return this.tproperties;
	}

	public void setTproperties(List<Tproperty> tproperties) {
		this.tproperties = tproperties;
	}

	public Tproperty addTproperty(Tproperty tproperty) {
		getTproperties().add(tproperty);
		tproperty.setTpropertyType(this);

		return tproperty;
	}

	public Tproperty removeTproperty(Tproperty tproperty) {
		getTproperties().remove(tproperty);
		tproperty.setTpropertyType(null);

		return tproperty;
	}

}