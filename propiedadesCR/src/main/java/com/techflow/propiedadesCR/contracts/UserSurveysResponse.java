package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.UserSurveyPOJO;

public class UserSurveysResponse extends BaseResponse{

	private List<UserSurveyPOJO> userSurveys;
	
	public UserSurveysResponse() {
		super();
	}

	public List<UserSurveyPOJO> getUserSurveys() {
		return userSurveys;
	}

	public void setUserSurveys(List<UserSurveyPOJO> userSurveys) {
		this.userSurveys = userSurveys;
	}
	
}
