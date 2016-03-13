package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.UserSurveysRequest;
import com.techflow.propiedadesCR.contracts.UserSurveysResponse;
import com.techflow.propiedadesCR.ejb.TuserSurvey;
import com.techflow.propiedadesCR.services.UserSurveysServiceInterface;

@RestController
@RequestMapping(value="rest/protected/usersurveys")
public class UserSurveysController {

	@Autowired private UserSurveysServiceInterface userSurveysService;

	@RequestMapping(value="/create",method=RequestMethod.POST)
	public UserSurveysResponse createUserSurvey(@RequestBody UserSurveysRequest puserSurveysRequest){
		UserSurveysResponse response = new UserSurveysResponse();
		TuserSurvey userSurvey = userSurveysService.createUserSurvey(puserSurveysRequest);
		if(userSurvey!=null)
			response.setCodeMessage("nice nice");
		return response;
	}

}
