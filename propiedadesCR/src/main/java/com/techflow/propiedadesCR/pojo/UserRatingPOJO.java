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

public class UserRatingPOJO {
	/**
     * Identificador del rating.
     */
	private int idRating;
	/**
     * Promedio del rating
     */
	private double averageRating;
	
	public int getIdRating() {
		return idRating;
	}
	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	
	
}
