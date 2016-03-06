package com.techflow.propiedadesCR.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.techflow.propiedadesCR.contracts.LoginRequest;
import com.techflow.propiedadesCR.repositories.LoginRepository;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;

@Service
public class LoginService implements LoginServiceInterface {

	@Autowired
	LoginRepository repositoryLogin;

	@Override
	public UserPOJO checkUser(LoginRequest ploginRequest) {
		UserPOJO userPOJO = null;
		Tuser user = repositoryLogin.findByEmailAndPassword(ploginRequest.getUserName(), ploginRequest.getPassword());

		if (null != user){
			userPOJO = new UserPOJO();
			BeanUtils.copyProperties(user, userPOJO);
		}	
		return userPOJO;
	}

}
