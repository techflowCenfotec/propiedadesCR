package com.techflow.propiedadesCR.controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.contracts.UsersResponse;
import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.services.UsersServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

@RestController
@RequestMapping(value="rest/protected/users")
public class UsersController {
	
	@Autowired private ServletContext servletContext;
	@Autowired private UsersServiceInterface usersService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public UsersResponse getAll(@RequestBody UsersRequest ur) {
		
		UsersResponse response = new UsersResponse();
		response.setCode(200);
		response.setCodeMessage("Users fetch successful");
		response.setUsers(usersService.getAll(ur));
		
		return response;
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public UsersResponse create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("idRol") int idRol,
			@RequestParam("userName") String userName,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("phone1") String phone1,
			@RequestParam("phone2") String phone2,
			@RequestParam("email") String email,
			@RequestParam("password") String password){	
		
		UsersResponse rs = new UsersResponse();
		String resultFileName = Utils.writeToFile(file,servletContext);
		if(!resultFileName.equals("")){
			
			UserPOJO user = new UserPOJO();
			user.setUserName(userName);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone1(phone1);
			user.setPhone2(phone2);
			user.setEmail(email);
			user.setPassword(password);
			user.setUserImage(resultFileName);
			user.setActive((byte)1);
			user.setFirstTime((byte)0);
			UsersRequest ur = new UsersRequest();
			ur.setUser(user);
			Tuser recentlyCreatedUser = usersService.saveUser(ur);
//		    TipoAlquilerPOJO userPOJO = new TipoAlquilerPOJO();
//						
//			TipoAlquiler tipoAlquiler = recentlyCreatedRent.getTipoAlquiler();
//			
//			AlquilerPOJO pojo = new AlquilerPOJO();
//			pojo.setDescription(recentlyCreatedRent.getDescription());
//			pojo.setName(recentlyCreatedRent.getName());
//			pojo.setIdAlquiler(recentlyCreatedRent.getIdAlquiler());
//			tipoAlquilerPOJO.setIdTipoAlquiler(tipoAlquiler.getIdTipoAlquiler());
//			pojo.setTipoAlquilerPOJO(tipoAlquilerPOJO);
			
			
		
			
//			rs.setAlquilerList(new ArrayList<AlquilerPOJO>());
//			rs.getAlquilerList().add(pojo);
			
			if(recentlyCreatedUser != null){
				rs.setCode(200);
				rs.setCodeMessage("User created ");
			}
			
		}else{
			rs.setCode(409);
			rs.setErrorMessage("create/edit conflict");
		}
	
		return rs;		
	}
	
}
