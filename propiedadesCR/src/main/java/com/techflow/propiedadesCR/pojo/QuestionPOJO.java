package com.techflow.propiedadesCR.pojo;

import java.util.List;
/**
* <h1>POJO de la pregunta</h1>
* 
* Enfatiza el uso de la clase simple "question"
* 
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/
public class QuestionPOJO {
	/**
     * Id de la pregunta
     */
	private int idQuestion;
	/**
     * Pregunta
     */
	private String question;
	/**
     * Lista de opciones de la pregunta
     */
	private List<OptionPOJO> toptions2;
	
	public QuestionPOJO() {
		super();
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<OptionPOJO> getToptions2() {
		return toptions2;
	}

	public void setToptions2(List<OptionPOJO> toptions2) {
		this.toptions2 = toptions2;
	}

}
