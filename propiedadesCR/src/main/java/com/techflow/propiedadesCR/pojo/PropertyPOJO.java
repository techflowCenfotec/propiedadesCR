package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.TpropertyComment;
import com.techflow.propiedadesCR.ejb.TpropertyRating;
import com.techflow.propiedadesCR.ejb.TpropertyType;
import com.techflow.propiedadesCR.ejb.Tuser;

/**
* <h1>POJO de la propiedad</h1>
* Clase que contiene los atributos de las imágenes.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class PropertyPOJO {
	
	/**
     * Id de la propiedad.
     */
	private int idProperty;
	/**
     * Estado de la propiedad. Utilizado para el borrado lógico de la propiedad.
     */
	private byte active;
	/**
     * Dirección exacta de la propiedad.
     */
	private String address;
	/**
     * Coordenadas de la propiedad.
     */
	private String coordinates;
	/**
     * Precio de la propiedad.
     */
	private double price;
	/**
     * Mteros de construcción de la propiedad.
     */
	private double squareMeters;
	/**
     * Lista de beneficios de la propiedad.
     */
	private List<BenefitsPOJO> tbenefits;
	/**
     * Distrito al que pertenece la propiedad.
     */
	private DistrictPOJO tdistrict;
	/**
     * Tipo de la propiedad.
     */
	private PropertyTypePOJO tpropertyType;
	/**
     * Usuario que registra la propiedad para la venta. Sólo usuarios de tipo vendedor.
     */
	private UserPOJO tuser;
	/**
     * Comentarios acerca la propiedad.
     */
	private List<CommentsPOJO> tpropertyComments; //Change to POJO
	/**
     * Calificación de la propiedad.
     */
	private List<RatingPOJO> tpropertyRatings; //Change to POJO
	/**
     * Lista de usuarios de la propiedad.
     */
	private List<UserPOJO> tusers;
	/**
     * Imágenes de la propiedad.
     */
	private List<PropertyImagePOJO> tpropertyImages;
	
	public PropertyPOJO() {
		super();
		this.tdistrict = new DistrictPOJO();
	}

	public int getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(int idProperty) {
		this.idProperty = idProperty;
	}

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(double squareMeters) {
		this.squareMeters = squareMeters;
	}

	public List<BenefitsPOJO> getTbenefits() {
		return tbenefits;
	}

	public void setTbenefits(List<BenefitsPOJO> tbenefits) {
		this.tbenefits = tbenefits;
	}

	public DistrictPOJO getTdistrict() {
		return tdistrict;
	}

	public void setTdistrict(DistrictPOJO tdistrict) {
		this.tdistrict = tdistrict;
	}

	public PropertyTypePOJO getTpropertyType() {
		return tpropertyType;
	}

	public void setTpropertyType(PropertyTypePOJO tpropertyType) {
		this.tpropertyType = tpropertyType;
	}

	public UserPOJO getTuser() {
		return tuser;
	}

	public void setTuser(UserPOJO tuser) {
		this.tuser = tuser;
	}

	public List<CommentsPOJO> getTpropertyComments() {
		return tpropertyComments;
	}

	public void setTpropertyComments(List<CommentsPOJO> tpropertyComments) {
		this.tpropertyComments = tpropertyComments;
	}

	public List<RatingPOJO> getTpropertyRatings() {
		return tpropertyRatings;
	}

	public void setTpropertyRatings(List<RatingPOJO> tpropertyRatings) {
		this.tpropertyRatings = tpropertyRatings;
	}

	public List<UserPOJO> getTusers() {
		return tusers;
	}

	public void setTusers(List<UserPOJO> tusers) {
		this.tusers = tusers;
	}

	public List<PropertyImagePOJO> getTpropertyImages() {
		return tpropertyImages;
	}

	public void setTpropertyImages(List<PropertyImagePOJO> tpropertyImages) {
		this.tpropertyImages = tpropertyImages;
	}
	
}
