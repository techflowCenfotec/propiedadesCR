package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.QuestionPOJO;

public class QuestionsRequest extends BaseRequest{
	
	private QuestionPOJO question;

	public QuestionsRequest() {
		super();
	}

	public QuestionPOJO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionPOJO question) {
		this.question = question;
	}

}
