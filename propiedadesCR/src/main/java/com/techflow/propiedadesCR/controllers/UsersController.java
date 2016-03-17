
/**
* <h1>Controlador de usuarios</h1>
* 
* Esta clase se encarga de realizar la 
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techflow.propiedadesCR.contracts.PropertiesResponse;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.contracts.UsersResponse;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.services.UsersServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

@RestController
@RequestMapping(value="rest/protected/users")
public class UsersController {
	
	/**
     * Este objeto se utiliza para el manejo de archivos. 
     */
	@Autowired private ServletContext servletContext;
	
	/** 
     * Este objeto proporciona los diferentes servicios para los usuarios
     */
	@Autowired private UsersServiceInterface usersService;
	
	/** 
     * Este objeto mantiene la sesión en el backend
     */
	@Autowired private HttpServletRequest httpServletRequest;
	
	/**
    * Este método retorna todos los usuarios registrados en el sistema
    *
    * @param puserResponse Este parámetro encapsula la información solicitada en el metodo.
	*
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
	  *
	  * @throws ParseException Esta exepción se lanza cuando el sistema es incapaz de transformar
	  * el String pbirthday a birthday ques es de tipo Date.
	   */
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
		String resultFileName;
		if(pfile.getOriginalFilename().equals("default_user_image_PropiedadesCR.png"))
		 resultFileName = "http://localhost:8080/propiedadesCR/resources/images/default_user_image.png";
		else			
		 resultFileName = Utils.writeToFile(pfile,servletContext);
		
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
			Tuser recentlyCreatedUser = usersService.saveUser(userRequest, pidRol);

			
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
	
	

	/**
    * Este método retorna el usuario loggeado en la aplicación
    *
    * @return response Retorna la respuesta del sevicio hacia el frontend.
    */
		@RequestMapping(value ="/getUserLogged", method = RequestMethod.GET)
		public UsersResponse getUserLogged(){
			UsersResponse response = new UsersResponse();
			response.setUser((UserPOJO)httpServletRequest.getSession().getAttribute("userLogged"));
			return response;
		}

		/**
	    * Este método retorna el usuario que se desea consultar
		*
	    * @param pidUser Identificador del usaurio que se consulta
	    * 
	    * @return response Retorna la respuesta del sevicio hacia el frontend.
	    */	
		@RequestMapping(value="/getUserById/{pidUser}", method = RequestMethod.GET)
		public UsersResponse getConsultedUser(
				@PathVariable int  pidUser){
			UsersResponse response = new UsersResponse();
			
			UserPOJO user = usersService.consultUser(pidUser);
			
			response.setUser(user);
			return response;
		}
		
		/**
		  * Envía la información a agregar a la base de datos por medio de su servicio. 
		  * 
		  * @param pPropRequest Petición que contiene la información de la entidad que
		  * se desea crear.
		  * @param pIdProperty Id de la propiedad. No debe ser nulo.
		  * @return response La entidad del objeto actualizado.
		  */
		@RequestMapping(value="addToFavorite/{pIdUser}", method = RequestMethod.PUT)
		public UsersResponse addToFavorite(@RequestBody Tproperty pProperty,
				@PathVariable int pIdUser) {
			UsersResponse response = new UsersResponse();
			List<Tproperty> properties = new ArrayList<Tproperty>();
			properties.add(pProperty);
			
			Tuser user = usersService.getUserByID(pIdUser);
			user.setTproperties2(properties);
			
			Tuser nUser = usersService.addToFavorite(user);
			
			if (nUser != null) {
				response.setCode(200);
				response.setCodeMessage("Property added to favorites");
			}
			
			return response;
		}
		
}
