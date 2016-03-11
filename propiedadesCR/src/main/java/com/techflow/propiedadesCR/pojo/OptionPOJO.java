package com.techflow.propiedadesCR.pojo;

public class OptionPOJO {
	
	private int idOption;
	private String option;
	private String result;
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
