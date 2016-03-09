package com.techflow.propiedadesCR.services;

import com.techflow.propiedadesCR.contracts.LoginRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;

public interface LoginServiceInterface {

	UserPOJO checkUser(LoginRequest ploginRequest);
}
