package com.techflow.propiedadesCR.controllers;

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
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.services.UsersServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

/**
* <h1>UsersController</h1>
* 
* Descripción de la clase.
* El controlador es el encargado de realizar la 
* comunicación entre el backend y el frontend.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 25/2/2016
*/
@RestController
@RequestMapping(value="rest/protected/users")
public class UsersController {
	
	/**	 
     * El objeto servletContext se utiliza para el manejo de archivos.     
     */
	@Autowired private ServletContext servletContext;
	/**
     * El objeto usersService proporsiona los diferentes servicios para los usuarios
     */
	@Autowired private UsersServiceInterface usersService;
	
	/**
	  * Descripción de lo que hace la función.
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @param userResponse Este parámetro encapsula la información solicitada en el metodo.
	
	  * @return response Retorna la respuesta del sevicio hacia el frontend.
	  */
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public UsersResponse getAll(@RequestBody UsersRequest userRequest) {
		
		UsersResponse response = new UsersResponse();
		response.setCode(200);
		response.setCodeMessage("Users fetch successful");
		response.setUsers(usersService.getAll(userRequest));
		
		return response;
	}
	
	/**
	  * Descripción de lo que hace la función.
	  * Este método registra un usuario en el sistema.
	  *
	  * @param file Imagen de perfil del usuario.
	  * @param idRol Rol del usuario en el sistema.
	  * @param userName Nombre de usuario.
	  * @param firstName Primer apellido del usuario.
	  * @param lastName Segundo apellido del usuario.
      * @param phone1 Teléfono del usuario.
      * @param phone2 Teléfono alternativo del usuario.
      * @param email Correo del usuario.
      * @param password Contraseña del usaurio.
      * 
	  * @return response Retorna la respuesta del servicio hacia el frontend.
	  */
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
		
		UsersResponse userResponse = new UsersResponse();
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
			UsersRequest userRequest = new UsersRequest();
			userRequest.setUser(user);
			Tuser recentlyCreatedUser = usersService.saveUser(userRequest);

			
			if(recentlyCreatedUser != null){
				userResponse.setCode(200);
				userResponse.setCodeMessage("User created ");
			}
			
		}else{
			userResponse.setCode(409);
			userResponse.setErrorMessage("create/edit conflict");
		}
	
		return userResponse;		
	}
	
}
