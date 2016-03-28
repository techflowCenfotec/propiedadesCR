package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.PasswordRequest;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;

public interface UsersServiceInterface {

	List<UserPOJO> getAll(UsersRequest puserRequest);
	List<UserPOJO> getAllVendors(UsersRequest puserRequest);
	Tuser saveUser(UsersRequest puserRequest,int pidRole);
	UserPOJO consultUser(int pidUser);

	Tuser modifyUser(UsersRequest puserRequest, int pidRole);
	Tuser getUserByEmail(String pemail);
	Tuser changePass(PasswordRequest ppasswordRequest);

	Tuser addToFavorite(Tuser pUser);
	Tuser getUserByID(int pIdUser);
	Tuser deleteUser(UsersRequest userRequest);
	UserPOJO consultVendor(int pidUser);


}
