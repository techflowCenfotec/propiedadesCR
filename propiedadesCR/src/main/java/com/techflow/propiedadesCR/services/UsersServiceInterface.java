package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.PasswordRequest;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.contracts.UsersResponse;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.pojo.UserPOJO;

public interface UsersServiceInterface {

	List<UserPOJO> getAll(UsersRequest puserRequest);
	UsersResponse getAllVendors(UsersRequest puserRequest);
	UserPOJO saveUser(UsersRequest puserRequest,int pidRole);
	UserPOJO consultUser(int pidUser);

	Tuser modifyUser(UsersRequest puserRequest, int pidRole);
	Tuser getUserByEmail(String pemail);
	Tuser changePass(PasswordRequest ppasswordRequest);
	Tuser updateFavorites(Tuser pUser);
	Tuser getUserByID(int pIdUser);

	UserPOJO consultVendor(int pidUser);
	Tuser getUserAdmin();
	Tuser deleteUser(UsersRequest puserRequest);
	List<PropertyPOJO> getAllFavorites(UsersRequest puserRequest);
	Tuser notFirstTime(UsersRequest puserRequest);
}
