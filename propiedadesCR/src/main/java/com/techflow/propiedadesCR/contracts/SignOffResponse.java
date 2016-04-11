package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserPOJO;

public class SignOffResponse extends BaseResponse{
	
	UserPOJO user;
	
	public SignOffResponse(){
		super();
	}
	
	public UserPOJO getUser(){
		return user;
	}
	
	public void setUser(UserPOJO puser){
		this.user = puser;
	}

	
}
