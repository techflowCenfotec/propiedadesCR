package com.techflow.propiedadesCR.pojo;

/**
* <h1>POJO de la calificación de la propiedad</h1>
* Clase que contiene los atributos de la calificación.
*
* @author  Walter Gómez
* @version 1.0
* @since 26/2/2016
*/
public class ReviewPropertyPOJO {
	
	/*
	 * Id de la calificación
	 */
	private int idReview;
	/*
	 * Promedio de las calificaciones de
	 * la propiedad.
	 */
	private double averageRating;
	/*
	 * Propiedad que es calificada.
	 */
	private PropertyPOJO tproperty;
	/*
	 * Usuario que da la calificación a
	 * la propiedad.
	 */
	private UserPOJO tuser;
	/*
	 * Comentario a la propiedad.
	 */
	private String comment;
	
	public ReviewPropertyPOJO() {
		super();
	}
	public int getIdReview() {
		return idReview;
	}
	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public PropertyPOJO getTproperty() {
		return tproperty;
	}
	public void setTproperty(PropertyPOJO tproperty) {
		this.tproperty = tproperty;
	}
	public UserPOJO getTuser() {
		return tuser;
	}
	public void setTuser(UserPOJO tuser) {
		this.tuser = tuser;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
