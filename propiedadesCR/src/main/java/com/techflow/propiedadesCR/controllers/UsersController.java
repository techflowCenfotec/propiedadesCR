
/**
* <h1>Controlador del usuario</h1>
* 
* El controlador es el encargado de realizar la 
* comunicación entre el backend y el frontend.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 25/2/2016
*/
package com.techflow.propiedadesCR.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @param puserResponse Este parámetro encapsula la información solicitada en el metodo.
	
	  * @return response Retorna la respuesta del sevicio hacia el frontend.
	  */
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public UsersResponse getAll(@RequestBody UsersRequest puserRequest) {
		
		UsersResponse response = new UsersResponse();
		response.setCode(200);
		response.setCodeMessage("Users fetch successful");
		response.setUsers(usersService.getAll(puserRequest));
		
		return response;
	}
	
	/**
	  * Este método registra un usuario en el sistema.
	  *
	  * @param pfile Imagen de perfil del usuario.
	  * @param pidRol Rol del usuario en el sistema.
	  * @param puserName Nombre de usuario.
	  * @param pfirstName Primer apellido del usuario.
	  * @param plastName Segundo apellido del usuario.
      * @param pphone1 Teléfono del usuario.
      * @param pphone2 Teléfono alternativo del usuario.
      * @param pemail Correo del usuario.
      * @param ppassword Contraseña del usaurio.
      * 
	  * @return response Retorna la respuesta del servicio hacia el frontend.
	  
	  * @throws java.text.ParseException */
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public UsersResponse create(
			@RequestParam("file") MultipartFile pfile,
			@RequestParam("idRol") int pidRol,
			@RequestParam("userName") String puserName,
			@RequestParam("firstName") String pfirstName,
			@RequestParam("lastName") String plastName,
			@RequestParam("phone1") String pphone1,
			@RequestParam("phone2") String pphone2,
			@RequestParam("email") String pemail,
			@RequestParam("password") String ppassword,
			@RequestParam("birthday") String pbirthday,
			@RequestParam("gender") String pgender){	
		
		
		
		Date birthday = new Date();
		  try {
			birthday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pbirthday);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UsersResponse userResponse = new UsersResponse();
		String resultFileName = Utils.writeToFile(pfile,servletContext);
		if(!resultFileName.equals("")){
			
			UserPOJO user = new UserPOJO();
			user.setUserName(puserName);
			user.setFirstName(pfirstName);
			user.setLastName(plastName);
			user.setPhone1(pphone1);
			user.setPhone2(pphone2);
			user.setEmail(pemail);
			user.setPassword(ppassword);
			user.setEmail(pemail);
			user.setBirthday(birthday);
			user.setGender(pgender);
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
