package com.techflow.propiedadesCR.pojo;

public class AnswerPOJO {
	private int idAnswer;
	private String result;
	private QuestionPOJO tquestion;
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
