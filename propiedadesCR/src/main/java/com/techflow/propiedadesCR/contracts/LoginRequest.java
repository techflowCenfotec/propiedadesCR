package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserPOJO;

public class LoginRequest extends BaseRequest{
	
	private String userName;
	private String password;
	
	public LoginRequest() {
		super();
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [User name=" + userName + "]";
	}
		
}
