package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.UserPOJO;

public class UsersResponse extends BaseResponse {

	private List<UserPOJO> users;
	
	public UsersResponse() {
		super();
	}
	
	public List<UserPOJO> getUsers() {
		return users;
	}
	
	public void setUsers(List<UserPOJO> users) {
		this.users = users;
	}
}
