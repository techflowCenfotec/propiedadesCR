package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.UserPOJO;

public class UsersResponse extends BaseResponse {

	private List<UserPOJO> users;
	
	private UserPOJO user;
	


	public UsersResponse() {
		super();
	}
	
	public List<UserPOJO> getUsers() {
		return users;
	}
	
	public void setUsers(List<UserPOJO> pusers) {
		this.users = pusers;
	}
	public UserPOJO getUser() {
		return user;
	}

	public void setUser(UserPOJO puser) {
		this.user = puser;
	}
}
