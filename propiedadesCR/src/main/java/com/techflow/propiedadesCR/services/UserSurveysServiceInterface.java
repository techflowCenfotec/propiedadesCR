package com.techflow.propiedadesCR.services;

import com.techflow.propiedadesCR.contracts.UserSurveysRequest;
import com.techflow.propiedadesCR.ejb.TuserSurvey;
import com.techflow.propiedadesCR.pojo.UserSurveyPOJO;

public interface UserSurveysServiceInterface {
	TuserSurvey createUserSurvey( UserSurveysRequest puserSurveysRequest);

	UserSurveyPOJO getUserSurveyById(int idUserSurvey);
}
