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
	
	public void setUser(UserPOJO puser) {
		this.user = puser;
	}
	
	@Override
	public String toString() {
		return "UsersRequest [user=" + user + "]";
	}
}
