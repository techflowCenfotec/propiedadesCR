package com.techflow.propiedadesCR.services;

import javax.servlet.http.HttpSession;

import com.techflow.propiedadesCR.contracts.LoginRequest;
import com.techflow.propiedadesCR.pojo.UserPOJO;

public interface LoginServiceInterface {

	UserPOJO checkUser(LoginRequest ploginRequest, HttpSession pcurrentSession);
}
