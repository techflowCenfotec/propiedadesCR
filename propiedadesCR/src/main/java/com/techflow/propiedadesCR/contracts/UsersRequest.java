/*
 * User Request
 * Esta clase se encarga de tomar datos que envia el cliente y genera el objeto con los respectivos datos. 
 * 
 * @author Jorge Arguedas Arrieta
 * @version 1.0
 * @since 25/02/2016.
 */

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
