package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserSurveyPOJO;

public class UserSurveysRequest extends BaseRequest{
	
	private UserSurveyPOJO userSurvey;

	public UserSurveysRequest() {
		super();
	}

	public UserSurveyPOJO getUserSurvey() {
		return userSurvey;
	}

	public void setUserSurvey(UserSurveyPOJO userSurvey) {
		this.userSurvey = userSurvey;
	}

}
