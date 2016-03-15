package com.techflow.propiedadesCR.pojo;
/**
* <h1>POJO de la opcion de una pregunta</h1>
* 
* Enfatiza el uso de la clase simple "option"
* 
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/
public class OptionPOJO {
	/**
     * Id de la opcion
     */
	private int idOption;
	/**
     * Opcion de la pregunta
     */
	private String option;
	/**
     * Resultado de seleccionar la opcion
     */
	private String result;
	/**
     * Id de la pregunta siguiente
     */
	private int idNextQuestion;
	
	public OptionPOJO(){
		super();
	}

	public int getIdOption() {
		return idOption;
	}

	public void setIdOption(int idOption) {
		this.idOption = idOption;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getIdNextQuestion() {
		return idNextQuestion;
	}

	public void setIdNextQuestion(int idNextQuestion) {
		this.idNextQuestion = idNextQuestion;
	}
	
}
