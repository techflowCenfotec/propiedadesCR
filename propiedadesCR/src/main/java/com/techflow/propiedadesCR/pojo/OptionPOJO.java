package com.techflow.propiedadesCR.pojo;

public class OptionPOJO {
	
	private int idOption;
	private String option;
	private String result;
	private QuestionPOJO tquestion1;
	private QuestionPOJO tquestion2;
	
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

	public QuestionPOJO getTquestion1() {
		return tquestion1;
	}

	public void setTquestion1(QuestionPOJO tquestion1) {
		this.tquestion1 = tquestion1;
	}

	public QuestionPOJO getTquestion2() {
		return tquestion2;
	}

	public void setTquestion2(QuestionPOJO tquestion2) {
		this.tquestion2 = tquestion2;
	}
	
	
}
