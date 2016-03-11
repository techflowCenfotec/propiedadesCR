package com.techflow.propiedadesCR.pojo;

import java.util.List;

public class UserSurveyPOJO {
	private int idSurvey;
	private List<AnswerPOJO> tanswers;
	private UserPOJO tuser;

	public UserSurveyPOJO() {
		super();
	}

	public int getIdSurvey() {
		return idSurvey;
	}

	public void setIdSurvey(int idSurvey) {
		this.idSurvey = idSurvey;
	}

	public List<AnswerPOJO> getTanswers() {
		return tanswers;
	}

	public void setTanswers(List<AnswerPOJO> tanswers) {
		this.tanswers = tanswers;
	}

	public UserPOJO getTuser() {
		return tuser;
	}

	public void setTuser(UserPOJO tuser) {
		this.tuser = tuser;
	}

	
}
