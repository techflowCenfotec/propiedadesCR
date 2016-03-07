package com.techflow.propiedadesCR.pojo;

/**
* <h1>POJO de las imágenes de la propiedad</h1>
* Clase que contiene los atributos de las imágenes.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class PropertyImagePOJO {

	/**
     * Id de la imágen de la propiedad.
     */
	private int idPropertyImage;
	/**
     * Ruta donde está ubicada la imágen de la propiedad.
     */
	private String propertyImage;
	/**
     * Propiedad a la que pertenece.
     */
	private PropertyPOJO tproperty;
	
	public PropertyImagePOJO() {
		super();
	}

	public int getIdPropertyImage() {
		return idPropertyImage;
	}

	public void setIdPropertyImage(int idPropertyImage) {
		this.idPropertyImage = idPropertyImage;
	}

	public String getPropertyImage() {
		return propertyImage;
	}

	public void setPropertyImage(String propertyImage) {
		this.propertyImage = propertyImage;
	}

	public PropertyPOJO getTproperty() {
		return tproperty;
	}

	public void setTproperty(PropertyPOJO tproperty) {
		this.tproperty = tproperty;
	}
}
