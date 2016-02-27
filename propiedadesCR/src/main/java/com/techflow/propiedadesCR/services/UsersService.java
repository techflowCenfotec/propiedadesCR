package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.security.*;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.repositories.UsersRepository;

@Service
public class UsersService implements UsersServiceInterface{
	
	@Autowired private UsersRepository usersRepository;
	
	@Override
	@Transactional
	public List<UserPOJO> getAll(UsersRequest ur) {
		List<Tuser> users = usersRepository.findAll();
		return generateUserDtos(users);
	}
	
	private List<UserPOJO> generateUserDtos(List<Tuser> properties) {
		List<UserPOJO> uiUsers = new ArrayList<UserPOJO>();
		properties.stream().forEach(u -> {
			UserPOJO dto = new UserPOJO();
			BeanUtils.copyProperties(u, dto);
			uiUsers.add(dto);
		});
		return uiUsers;
	}

	@Override
	public Tuser saveUser(UsersRequest ur) {
		
		Tuser user = new Tuser();
		BeanUtils.copyProperties(ur.getUser(), user);
		//Combertir el pass a md5
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			user.setPassword(md.digest(user.getPassword().getBytes()).toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Tuser nuser = usersRepository.save(user);
		
		return nuser;
	
	}

}
