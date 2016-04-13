
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

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techflow.propiedadesCR.contracts.MailRequest;
import com.techflow.propiedadesCR.contracts.PropertiesResponse;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.contracts.UsersResponse;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.EventPOJO;
import com.techflow.propiedadesCR.pojo.RolePOJO;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.services.UsersServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

@RestController
@RequestMapping(value = "rest/protected/users")
public class UsersController {

	/**
	 * Este objeto se utiliza para el manejo de archivos.
	 */
	@Autowired
	private ServletContext servletContext;

	/**
	 * Este objeto proporciona los diferentes servicios para los usuarios
	 */
	@Autowired
	private UsersServiceInterface usersService;

	/**
	 * Este objeto mantiene la sesión en el backend
	 */
	@Autowired
	private HttpServletRequest httpServletRequest;

	/**
	 * Este método retorna todos los usuarios registrados en el sistema
	 *
	 * @param puserResponse
	 *            Este parámetro encapsula la información solicitada en el
	 *            metodo.
	 *
	 * @return response Retorna la respuesta del sevicio hacia el frontend.
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)

	public UsersResponse getAll(@RequestBody UsersRequest puserRequest) {

		UsersResponse response = new UsersResponse();
		response.setCode(200);
		response.setCodeMessage("Users fetch successful");
		response.setUsers(usersService.getAll(puserRequest));

		return response;
	}

	/**
	 *    * Este método retorna todos los usuarios vendedores registrados en el
	 * sistema
	 *
	 * @param puserResponse
	 *            Este parámetro encapsula la información solicitada en el
	 *            metodo.
	 *
	 * @return response Retorna la respuesta del sevicio hacia el frontend.   
	 */

	@RequestMapping(value = "/getAllVendors", method = RequestMethod.POST)
	public UsersResponse getAllVendors(@RequestBody UsersRequest puserRequest) {

		UsersResponse response = new UsersResponse();
		response.setCode(200);
		response.setCodeMessage("Users fetch successful");
		response.setUsers(usersService.getAllVendors(puserRequest));

		return response;
	}

	/**
	 * Este método registra un usuario en el sistema.
	 *
	 * @param pfile
	 *            Imagen de perfil del usuario.
	 * @param pidRol
	 *            Rol del usuario en el sistema.
	 * @param puserName
	 *            Nombre de usuario.
	 * @param pfirstName
	 *            Primer apellido del usuario.
	 * @param plastName
	 *            Segundo apellido del usuario.
	 * @param pphone1
	 *            Teléfono del usuario.
	 * @param pphone2
	 *            Teléfono alternativo del usuario.
	 * @param pemail
	 *            Correo del usuario.
	 * @param ppassword
	 *            Contraseña del usaurio.
	 * @param pbirthday
	 *            Fecha de nacimiento del usuario.
	 * @param pgender
	 *            Género del usuario.
	 * 
	 *              * @return userResponse Retorna la respuesta del servicio
	 *            hacia el frontend.   *
	 * @throws ParseException
	 *             Esta exepción se lanza cuando el sistema es incapaz de
	 *             transformar el String pbirthday a birthday que es de tipo
	 *             Date.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public UsersResponse create(@RequestParam("file") MultipartFile pfile, @RequestParam("idRol") int pidRole,
			@RequestParam("userName") String puserName, @RequestParam("firstName") String pfirstName,
			@RequestParam("lastName") String plastName, @RequestParam("phone1") String pphone1,
			@RequestParam("phone2") String pphone2, @RequestParam("email") String pemail,
			@RequestParam("password") String ppassword, @RequestParam("birthday") String pbirthday,
			@RequestParam("gender") String pgender) {
		String  pass ="";
		Date birthday = new Date();
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pbirthday);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UsersResponse userResponse = new UsersResponse();
		String resultFileName;
		if (pfile.getOriginalFilename().equals("default_user_image_PropiedadesCR.png"))
			resultFileName = "http://localhost:8080/propiedadesCR/resources/images/default_user_image.png";
		else
			resultFileName = Utils.writeToFile(pfile, servletContext);

		if (!resultFileName.equals("")) {

			UserPOJO user = new UserPOJO();
			user.setUserName(puserName);
			user.setFirstName(pfirstName);
			user.setLastName(plastName);
			user.setPhone1(pphone1);
			user.setPhone2(pphone2);
			user.setEmail(pemail);
			if(ppassword.equals("null")){
				pass = generateRandomPassword();
				user.setPassword(pass);
			}else
				user.setPassword(ppassword);
			user.setEmail(pemail);
			user.setBirthday(birthday);
			user.setGender(pgender);
			user.setUserImage(resultFileName);
			user.setActive((byte) 1);
			user.setFirstTime((byte) 0);
			UsersRequest userRequest = new UsersRequest();
			userRequest.setUser(user);
			UserPOJO recentlyCreatedUser = usersService.saveUser(userRequest, pidRole);

			if (recentlyCreatedUser != null) {
				recentlyCreatedUser.setPassword(pass);
				userResponse.setUser(recentlyCreatedUser);
				userResponse.setCode(200);
				userResponse.setCodeMessage("User created ");
			}

		} else {
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
	@RequestMapping(value = "/getUserLogged", method = RequestMethod.GET)
	public UsersResponse getUserLogged() {
		UsersResponse response = new UsersResponse();
		response.setUser((UserPOJO) httpServletRequest.getSession().getAttribute("userLogged"));
		return response;
	}

	/**
	 * Este método retorna el usuario que se desea consultar
	 *
	 * @param pidUser
	 *            Identificador del usaurio que se consulta
	 * 
	 * @return response Retorna la respuesta del sevicio hacia el frontend.
	 */
	@RequestMapping(value = "/getUserById/{pidUser}", method = RequestMethod.GET)
	public UsersResponse getConsultedUser(@PathVariable int pidUser) {
		UsersResponse response = new UsersResponse();

		UserPOJO user = usersService.consultUser(pidUser);

		response.setUser(user);
		return response;
	}

	/**
		  * Este método registra un usuario en el sistema.
		  *
		  * @param pfile Imagen de perfil del usuario.
		  * @param pidUser Identificador del usuario a modificar.
		  * @param pidRol Rol del usuario en el sistema.
	      * @param puserName Nombre de usuario.
		  * @param pfirstName Primer apellido del usuario.
		  * @param plastName Segundo apellido del usuario.
	      * @param pphone1 Teléfono del usuario.
	      * @param pphone2 Teléfono alternativo del usuario.
	      * @param pemail Correo del usuario.
	      * @param ppassword Contraseña del usaurio.
	      * @param pbirthday Fecha de nacimiento del usuario.
	      * @param pgender Género del usuario.
	      *
		  * @return userResponse Retorna la respuesta del servicio hacia el frontend.
		  *
		  * @throws ParseException Esta exepción se lanza cuando el sistema es incapaz de transformar
		  * el String pbirthday a birthday ques es de tipo Date.
		  */
		@RequestMapping(value="/modifyUser", method = RequestMethod.POST)
		public UsersResponse modifyUser(@RequestParam("file") MultipartFile pfile,
				@RequestParam("idUser") int pidUser,
				@RequestParam("idRol") int pidRole,
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
		if (pfile.getSize() == 0)
			resultFileName = pfile.getOriginalFilename();
		else
			resultFileName = Utils.writeToFile(pfile, servletContext);

		if (!resultFileName.equals("")) {

			UserPOJO user = new UserPOJO();
			user.setIdUser(pidUser);
			user.setUserName(puserName);
			user.setFirstName(pfirstName);
			user.setLastName(plastName);
			user.setPhone1(pphone1);
			user.setPhone2(pphone2);
			user.setEmail(pemail);
			user.setPassword(ppassword);
			user.setPassword(ppassword);
			user.setEmail(pemail);
			user.setBirthday(birthday);
			user.setGender(pgender);
			user.setUserImage(resultFileName);
			user.setActive((byte) 1);
			user.setFirstTime((byte) 0);
			UsersRequest userRequest = new UsersRequest();
			userRequest.setUser(user);
			Tuser recentlyCreatedUser = usersService.modifyUser(userRequest, pidRole);
			UserPOJO userPOJO = new UserPOJO();
			BeanUtils.copyProperties(recentlyCreatedUser, userPOJO);
			if (recentlyCreatedUser != null) {
				RolePOJO role = new RolePOJO();
				BeanUtils.copyProperties(recentlyCreatedUser.getTrole(), role);
				userPOJO.setRole(role);
				userResponse.setUser(userPOJO);
				userResponse.setCode(200);
				userResponse.setCodeMessage("User modified ");
			}

		} else {
			userResponse.setCode(409);
			userResponse.setErrorMessage("create/edit conflict");
		}

		return userResponse;

	}

	/**
	 * Envía la información a agregar a la base de datos por medio de su
	 * servicio.
	 * 
	 * @param pProperty
	 *            Ejb que contiene la información de la entidad que se desea
	 *            crear.
	 * @param pIdProperty
	 *            Id de la propiedad. No debe ser nulo.
	 * @return response La entidad del objeto actualizado.
	 */
	@RequestMapping(value = "addToFavorite/{pIdUser}", method = RequestMethod.PUT)
	public UsersResponse addToFavorite(@RequestBody Tproperty pProperty, @PathVariable int pIdUser) {
		UsersResponse response = new UsersResponse();

		Tuser user = usersService.getUserByID(pIdUser);

		List<Tproperty> properties = user.getTproperties2();
		properties.add(pProperty);

		user.setTproperties2(properties);

		Tuser nUser = usersService.updateFavorites(user);

		if (nUser != null) {
			response.setCode(200);
			response.setCodeMessage("Property added to favorites");
		}


		return response;
	}
	
	/**
	 * Este método realiza un borrado logico al usuario
	 * 
	 * @param puserRequest
	 *            Encapsula la información del correo.
	 *
	 * @return response Retorna la respuesta del backend.
	 */

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public UsersResponse getConsultedUser(@RequestBody UsersRequest puserRequest) {
		UsersResponse userResponse = new UsersResponse();

		Tuser recentlyCreatedUser = usersService.deleteUser(puserRequest);

		if (recentlyCreatedUser != null) {
			userResponse.setCode(200);
			userResponse.setCodeMessage("User modified ");
		}

		return userResponse;
	}

	/**
	 * Este método retorna el usuario que se desea consultar
	 *
	 * @param pidUser
	 *            Identificador del usaurio que se consulta
	 * 
	 * @return response Retorna la respuesta del sevicio hacia el frontend.
	 */
	@RequestMapping(value = "/getVendorById/{pidUser}", method = RequestMethod.GET)
	public UsersResponse getVendor(@PathVariable int pidUser) {
		UsersResponse response = new UsersResponse();

		UserPOJO user = usersService.consultVendor(pidUser);

		response.setUser(user);
		return response;
	}

		/**
		  * Envía la información a agregar a la base de datos por medio de su servicio. 
		  * 
		  * @param pProperty Ejb que contiene la información de la entidad que
		  * se desea remover de favoritos.
		  * @param pIdUser Id del usuario. No debe ser nulo.
		  * @return response La entidad del objeto actualizado.
		  */
		@RequestMapping(value="updateFavorite/{pIdUser}", method = RequestMethod.PUT)
		public UsersResponse updateFavorite(@RequestBody Tproperty pProperty,
				@PathVariable int pIdUser) {
			UsersResponse response = new UsersResponse();
			Boolean exists = true;
			Tuser user = usersService.getUserByID(pIdUser);
			List<Tproperty> properties = user.getTproperties2();
			
			if(properties.isEmpty()) {
				properties.add(pProperty);
				exists = false;
			} else {
				for (int i = 0; i < properties.size(); i++) {
					if (properties.get(i).getIdProperty() == pProperty.getIdProperty()) {
						properties.remove(i);
						exists = false;
					} 
				}
			}
			
			if (exists == true){
				properties.add(pProperty);
			}
			
			user.setTproperties2(properties);
			
			Tuser nUser = usersService.updateFavorites(user);
			
			if (nUser != null) {
				response.setCode(200);
				response.setCodeMessage("Property removed form favorites");
			}
			
			return response;
		}
		
		@RequestMapping(value="/welcomeEmail", method = RequestMethod.POST)
		public UsersResponse sendEmail(@RequestBody UsersRequest puserRequest){
			 
			 UsersResponse response = new UsersResponse();
			  
		      String to = puserRequest.getUser().getEmail();
		      
		      String from = "propiedadescr.tech@gmail.com";
		      final String username = "propiedadescr.tech@gmail.com";
		      final String password = "mjjvwTechflow";

		      
		      String host = "smtp.gmail.com";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", "587");

		      
		      Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		         protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		         }
		      });

		      try {
		         
		         Message message = new MimeMessage(session);

		         
		         message.setFrom(new InternetAddress(from));

		        
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));

		         
		         message.setSubject("Bienvenido a PropiedadesCR");
		         
		         message.setContent("<!Doctype html>"
		        		  +"     <html>"
		        		  +"  	 <head>"
		        		  +"  	 <meta charset='utf-8'>"
		        		  +"  	 </head>"
		        		  +"  	  <body>"
		        		  +"  	  <div style='padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;width: 970px;'>"
		        		  +"  	  		<div>"
		        		  +"  	  			<h2 style=' color: #777;'>PropiedadesCR</h2>"
		        		  +"  	   		</div>"
		        		  +"  	       		<div style='margin-right: -15px; margin-left: -15px;'>"
		        		  +"    	        		<div class='col-xs-12 col-md-8'>"
		        		  +"  	        			<div style='padding-top: 30px;padding-bottom: 30px;margin-bottom: 30px;color: inherit;background-color: #eee;padding-right: 60px;padding-left: 60px;'>"
		        		  +"  	      					<h3>Bienvenido a PropiedadesCR</h3>"
		        		  +"  	        				<p style='margin-bottom: 20px;font-size: 16px;font-weight: 300;ine-height: 1.4;'>Estimado usuario gracias por haberse unido a la más grande empresa de bienes raices de Costa Rica, toda la comunidad de PropiedadesCR le desea lo mejor.</p>"
		        		  +"  	        				<p style='margin-bottom: 20px;font-size: 16px;font-weight: 300;ine-height: 1.4;'>Su contraseña es " +puserRequest.getUser().getPassword()+"</p>"
		        		  +"  	         				<p style='margin-bottom: 20px;font-size: 16px;font-weight: 300;ine-height: 1.4;'>Esperamos cumplir con sus expectativas</p>"
		        		  +"  	        			</div>"
		        		  +"  	        		</div>"
		        		  +"  	        </div>"
		        		  +"  	   </div>"
		        		  +"   	</body>"
		        		  +"  </html>","text/html");
		         
		         Transport.send(message);
		         response.setCode(200);

		        

		      } catch (MessagingException e) {
		            throw new RuntimeException(e);
		      }
		      return response;
		}

		  /**
		   * Este metodo genera una contraseña al azar.
		   *
		   * @return Retorna la contraseña.
		   */
		  public static String generateRandomPassword()
		  {
			  Random RANDOM = new SecureRandom();
			  int PASSWORD_LENGTH = 8;
		      String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
		      String password = "";
		      for (int i=0; i<PASSWORD_LENGTH; i++)
		      {
		          int index = (int)(RANDOM.nextDouble()*letters.length());
		          password += letters.substring(index, index+1);
		      }
		      return password;
		  }
		  
		  /**
		   * Este metodo se encarga de devolver una lista con las propiedades
		   * favoritas del usuario.
		   * @author Valeria Ramírez Cordero
		   * @param puserRequest 
		   * @return Retorna la contraseña.
		   */
			@RequestMapping(value = "/getMyFavoriteProperties", method = RequestMethod.POST)

			public PropertiesResponse getMyFavoriteProperties(@RequestBody UsersRequest puserRequest) {

				PropertiesResponse response = new PropertiesResponse();
				response.setCode(200);
				response.setCodeMessage("List of favorites success!");
				response.setProperties(usersService.getAllFavorites(puserRequest)); 

				return response;
			}


}
