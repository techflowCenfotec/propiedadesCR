package com.techflow.propiedadesCR.services;

import com.techflow.propiedadesCR.contracts.UserSurveysRequest;
import com.techflow.propiedadesCR.ejb.TuserSurvey;

public interface UserSurveysServiceInterface {
	TuserSurvey createUserSurvey( UserSurveysRequest puserSurveysRequest);
}
