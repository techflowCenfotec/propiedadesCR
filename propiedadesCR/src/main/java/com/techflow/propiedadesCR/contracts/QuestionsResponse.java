package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.QuestionPOJO;

public class QuestionsResponse extends BaseResponse{
	
	private List<QuestionPOJO> questions;

	public QuestionsResponse() {
		super();
	}

	public List<QuestionPOJO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionPOJO> questions) {
		this.questions = questions;
	}

}
