package com.techflow.propiedadesCR.pojo;
/**
* <h1>POJO de la respuesta</h1>
* 
* Enfatiza el uso de la clase simple "answer"
* 
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/
public class AnswerPOJO {
	/**
     * Id de la pregunta
     */
	private int idAnswer;
	/**
     * Respuesta o Tag
     */
	private String result;
	/**
     * Pregunta a la que pertenece la respuesta
     */
	private QuestionPOJO tquestion;
	/**
     * Cuestionario a la que pertenece la respuesta
     */
	private UserSurveyPOJO TuserSurvey;
	
	public AnswerPOJO(){
		super();
	}

	public int getIdAnswer() {
		return idAnswer;
	}

	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public QuestionPOJO getTquestion() {
		return tquestion;
	}

	public void setTquestion(QuestionPOJO tquestion) {
		this.tquestion = tquestion;
	}

	public UserSurveyPOJO getTuserSurvey() {
		return TuserSurvey;
	}

	public void setTuserSurvey(UserSurveyPOJO tuserSurvey) {
		TuserSurvey = tuserSurvey;
	}
	
}
