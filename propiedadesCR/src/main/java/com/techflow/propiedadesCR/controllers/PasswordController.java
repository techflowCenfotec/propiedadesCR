
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
* @since 14/3/2016
*/
package com.techflow.propiedadesCR.controllers;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.PasswordRequest;
import com.techflow.propiedadesCR.contracts.PasswordResponse;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.services.UsersServiceInterface;

@RestController
@RequestMapping(value = "rest/password")
public class PasswordController {

	@Autowired
	UsersServiceInterface userService;

	/**
	 * Este método envía un correo.
	 * @param pmailInformation Encapsula la información del correo.
	 * @exception IOException Esta excepción se lanza cuando ocurre un error al enviar el correo.
	 */
	
	@RequestMapping(value="/sendEmail", method = RequestMethod.POST)
	public PasswordResponse sendEmail(@RequestBody PasswordRequest pmailInformation){
		
		Tuser user = new Tuser();
		user = userService.getUserByEmail(pmailInformation.getUserEmail());
		PasswordResponse response = new PasswordResponse();
		if(user!=null){
	
		
		  response.setIdUser(user.getIdUser());
	      String to = pmailInformation.getUserEmail();
	      
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

	         
	         message.setSubject("Reiniciar contraseña");
	         
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
	  +"  	      					<h3>Cambio de contraseña</h3>"
	  +"  	        				<p style='margin-bottom: 20px;font-size: 16px;font-weight: 300;ine-height: 1.4;'>Estimado usuario se nos ha notificado que se ha pedido hacer un cambio de contraseña de ser así por favor hacer clic en 'Cambiar contraseña',de no ser así omita este mensaje.</p>"
	  +"  	        				<p style='margin-bottom: 20px;font-size: 16px;font-weight: 300;ine-height: 1.4;'>Un cordial saludo</p>"
	  +"  	         				<a href='http://localhost:8080/propiedadesCR/#/templates/resetPasswordView/resetPassword'>Cambiar contrase&ntilde;a</a>"
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
		}else
			response.setCode(500);
		
		return response;
	      
	}

	/**
	 *    * Este método se encarga de cambiar la contraseña del usuario
	 *
	 *    * @param ppaswordRequest Este parámetro encapsula la información
	 * solicitada en el metodo.
	 *
	 *   * @return response Retorna la respuesta del sevicio hacia el frontend.
	 *   
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public PasswordResponse changePassword(@RequestBody PasswordRequest ppaswordRequest) {
		PasswordResponse response = new PasswordResponse();

		if (userService.changePass(ppaswordRequest) != null)
			response.setCode(200);
		else
			response.setCode(500);

		return response;
	}

}
