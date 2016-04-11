/**
* <h1>POJO del Rating del usuario</h1>
*Esta clase enfatiza el uso de la clase simple "usuario".
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 3/12/2016
*/
package com.techflow.propiedadesCR.pojo;

public class UserReviewPOJO {
	/**
     * Identificador del rating.
     */
	private int idReview;
	/**
     * Promedio del rating
     */
	private double averageRating;
	/**
	 * Usuario cliente
	 */
	private UserPOJO Tuser1;
	/**
	 * Comentario del cliente
	 */
	private String comment;
	
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
	public UserPOJO getTuser1() {
		return Tuser1;
	}
	public void setTuser1(UserPOJO tuser1) {
		Tuser1 = tuser1;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
