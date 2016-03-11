package com.techflow.propiedadesCR.pojo;

import java.util.List;

public class QuestionPOJO {

	private int idQuestion;
	private byte active;
	private String question;
	private List<AnswerPOJO> tanswers;
	private List<OptionPOJO> toptions1;
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

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<AnswerPOJO> getTanswers() {
		return tanswers;
	}

	public void setTanswers(List<AnswerPOJO> tanswers) {
		this.tanswers = tanswers;
	}

	public List<OptionPOJO> getToptions1() {
		return toptions1;
	}

	public void setToptions1(List<OptionPOJO> toptions1) {
		this.toptions1 = toptions1;
	}

	public List<OptionPOJO> getToptions2() {
		return toptions2;
	}

	public void setToptions2(List<OptionPOJO> toptions2) {
		this.toptions2 = toptions2;
	}

}
