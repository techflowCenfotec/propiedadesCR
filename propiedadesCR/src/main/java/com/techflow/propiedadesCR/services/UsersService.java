package com.techflow.propiedadesCR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.repositories.UsersRepository;

@Service
public class UsersService implements UsersServiceInterface {

	@Autowired private UsersRepository usersRepository;
	
	@Override
	public List<UserPOJO> getAll(UsersRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPOJO> getAllByName(UsersRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean saveUser(UsersRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPOJO> getAlquileres(UsersRequest ur) {
		// TODO Auto-generated method stub
		return null;
	}

}
