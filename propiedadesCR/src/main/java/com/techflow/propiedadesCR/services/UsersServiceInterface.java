package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;

public interface UsersServiceInterface {
	
	List<UserPOJO> getAll(UsersRequest pr);
	Tuser saveUser(UsersRequest pr);
}
