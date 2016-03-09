package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserPOJO;

public class LoginResponse {

	UserPOJO user;

	public LoginResponse() {
		super();
	}

	public UserPOJO getUser() {
		return user;
	}

	public void setUser(UserPOJO puser) {
		this.user = puser;
	}

}
