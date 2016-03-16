package com.techflow.propiedadesCR.pojo;

import java.util.List;
/**
* <h1>POJO del questionario del usuario</h1>
* 
* Enfatiza el uso de la clase simple "user survey"
* 
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/
public class UserSurveyPOJO {
	/**
     * Id del cuestionario
     */
	private int idSurvey;
	/**
     * Lista de respuestas del cuestionario
     */
	private List<AnswerPOJO> tanswers;
	/**
     * Id del usuario que realizo la encuesta
     */
	private UserPOJO tuser;

	public UserSurveyPOJO() {
		super();
	}

	public int getIdSurvey() {
		return idSurvey;
	}

	public void setIdSurvey(int idSurvey) {
		this.idSurvey = idSurvey;
	}

	public List<AnswerPOJO> getTanswers() {
		return tanswers;
	}

	public void setTanswers(List<AnswerPOJO> tanswers) {
		this.tanswers = tanswers;
	}

	public UserPOJO getTuser() {
		return tuser;
	}

	public void setTuser(UserPOJO tuser) {
		this.tuser = tuser;
	}

	
}
