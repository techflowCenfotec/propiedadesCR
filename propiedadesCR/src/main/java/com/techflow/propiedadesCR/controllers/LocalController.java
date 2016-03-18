
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
* @since 18/3/2016
*/
package com.techflow.propiedadesCR.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techflow.propiedadesCR.contracts.PasswordRequest;
import com.techflow.propiedadesCR.contracts.PasswordResponse;
import com.techflow.propiedadesCR.contracts.RolesRequest;
import com.techflow.propiedadesCR.contracts.RolesResponse;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.contracts.UsersResponse;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.services.RolesServiceInterface;
import com.techflow.propiedadesCR.services.UsersServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

@RestController
@RequestMapping(value="rest/local")
public class LocalController {
	
	
	/**
     * Este objeto se utiliza para el manejo de archivos. 
     */
	@Autowired private ServletContext servletContext;
	
	/** 
     * Este objeto proporciona los diferentes servicios para los usuarios
     */
	@Autowired private UsersServiceInterface usersService;
	
	/**
	 * Objeto que ofrece servicios de los roles
	 *
	 */ 	
	@Autowired private RolesServiceInterface rolesService;

	
	
	/**
	 * Este método envía un correo.
	 * @param pmailInformation Encapsula la información del correo.
	 * @exception IOException Esta excepción se lanza cuando ocurre un error al enviar el correo.
	 */
	
	@RequestMapping(value="/sendEmail", method = RequestMethod.POST)
	public PasswordResponse sendEmail(@RequestBody UsersRequest puseRequest){
		
	
		PasswordResponse response = new PasswordResponse();
		

	      String to = puseRequest.getUser().getEmail();
	      
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

	         
	         message.setSubject("PropiedadesCR");
	         
	         message.setContent("<h3>Bienvenido a PropiedadesCR</h3>"+
	        		"<p>Estimado usuario gracias por haberse unido a la más grande empresa de bienes raices</p>"+
	        		"<p>de ser Costa Rica, toda la comunidad de PropiedadesCR le desea lo mejor.</p>"+
	        		"<p>Esperamos cumplir con sus expectativas</p>","text/html");
	         
	         Transport.send(message);
	         response.setCode(200);
	        

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	
			response.setCode(500);
		
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
		  * @return userResponse Retorna la respuesta del servicio hacia el frontend.
		  *
		  * @throws ParseException Esta exepción se lanza cuando el sistema es incapaz de transformar
		  * el String pbirthday a birthday que es de tipo Date.
		   */
		@RequestMapping(value ="/createUser", method = RequestMethod.POST)
		public UsersResponse create(
				@RequestParam("file") MultipartFile pfile,
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
				Tuser recentlyCreatedUser = usersService.saveUser(userRequest, pidRole);

				
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
		 * Este método permite obtener todos los roles que se encuetran
		 * en el sistema
		 * 
		 * @param prolesRequest Este parámetro es la peticion del front-end
		 * que se utiliza para acceder al método deseado
		 * 
		 * @return rolesResponse Resultado que contiene la lista de roles que se ecuntran
		 * en el sistema
		 *
		 */ 
		@RequestMapping(value ="/getAll", method = RequestMethod.POST)
		
		public RolesResponse getAll(@RequestBody RolesRequest prolesRequest){	
			RolesResponse rolesResponse = new RolesResponse();
			rolesResponse.setCode(200);
			rolesResponse.setCodeMessage("roles fetch success");
			rolesResponse.setRole(rolesService.getAll(prolesRequest)); 
			return rolesResponse;		
		}

}
