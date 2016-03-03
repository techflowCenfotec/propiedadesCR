package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tproperty_image database table.
 * 
 */
@Entity
@Table(name="tproperty_image")
@NamedQuery(name="TpropertyImage.findAll", query="SELECT t FROM TpropertyImage t")
public class TpropertyImage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idPropertyImage;
	private String propertyImage;
	private Tproperty tproperty;

	public TpropertyImage() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_property_image")
	public int getIdPropertyImage() {
		return this.idPropertyImage;
	}

	public void setIdPropertyImage(int idPropertyImage) {
		this.idPropertyImage = idPropertyImage;
	}


	@Column(name="property_image")
	public String getPropertyImage() {
		return this.propertyImage;
	}

	public void setPropertyImage(String propertyImage) {
		this.propertyImage = propertyImage;
	}


	//bi-directional many-to-one association to Tproperty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_property")
	public Tproperty getTproperty() {
		return this.tproperty;
	}

	public void setTproperty(Tproperty tproperty) {
		this.tproperty = tproperty;
	}

}