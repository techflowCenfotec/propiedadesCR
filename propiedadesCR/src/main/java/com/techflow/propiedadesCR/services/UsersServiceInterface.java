package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.pojo.UserPOJO;

public interface UsersServiceInterface {

	List<UserPOJO> getAll(UsersRequest ur);
	List<UserPOJO> getAllByName(UsersRequest ur);
	Boolean saveUser(UsersRequest ur);
	List<UserPOJO> getAlquileres(UsersRequest ur);
}
