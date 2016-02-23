package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserPOJO;

public class UsersRequest extends BaseRequest {

	UserPOJO user;
	
	public UsersRequest() {
		super();
	}
	
	public UserPOJO getUser() {
		return user;
	}
	
	public void setUser(UserPOJO user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "UsersRequest [user=" + user + "]";
	}
}
