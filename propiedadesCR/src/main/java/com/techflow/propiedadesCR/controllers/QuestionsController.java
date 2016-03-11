package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.QuestionsRequest;
import com.techflow.propiedadesCR.contracts.QuestionsResponse;
import com.techflow.propiedadesCR.services.QuestionsServiceInterface;

@RestController
@RequestMapping(value = "rest/protected/questions")
public class QuestionsController {
	
	@Autowired private QuestionsServiceInterface questionsService;
	
	@RequestMapping(value="/getQuestionsWithOptions", method= RequestMethod.POST)
	public QuestionsResponse getQuestionsWithOptions(@RequestBody QuestionsRequest pquestionRequest){
		QuestionsResponse response = new QuestionsResponse();
		response.setQuestions(questionsService.getQuestionsWithOptions());
		return response;
	}

}
