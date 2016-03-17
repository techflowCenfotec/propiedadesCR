package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.pojo.QuestionPOJO;

public interface QuestionsServiceInterface{
	List<QuestionPOJO> getQuestionsWithOptions();
}