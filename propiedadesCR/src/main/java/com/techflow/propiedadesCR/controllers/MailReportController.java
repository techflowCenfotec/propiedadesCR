/**
 * <h1>Controlador de correo de reportes de usuarios</h1>
 * Esta clase es  encargada de recibir la información entre el BackEnd y el FrondEnd
 * 
 * @author Valeria Ramírez Cordero.
 * @version 1.0
 * @since 24/03/2016
 */
package com.techflow.propiedadesCR.controllers;

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

import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.services.UsersServiceInterface;

@RestController
@RequestMapping(value="rest/protected/AdminEmail")
public class MailReportController {
	/**
	 * Este método envía el correo.
	 * @param pmailInformation Encapsula la información que contiene los datos del usuario
	 * a ser reportado.
	 * @exception IOException Esta excepción se lanza cuando ocurre un error al enviar el correo.
	 */
	@Autowired private UsersServiceInterface usersService;
	@RequestMapping(value="/sendEmail", method = RequestMethod.POST)
	public void sendEmail(@RequestBody UsersRequest pmailInformation){
		  
		  UserPOJO userInfo = pmailInformation.getUser();
	      String to = "propiedadescr.tech@gmail.com";
	      
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

	         
	         message.setSubject("Reporte de usuario");

	         
	         message.setText("Se ha reportado el siguiente vendedor. \n"
	         		+ "\n A continuación encontrará la información del vendedor reportado."
	         		+ "\n Nombre: "+ userInfo.getUserName()
	         		+ "\n Apellido:"+ userInfo.getFirstName()
	         		+ "\n Correo: "+ userInfo.getEmail()
	         		+ "\n Gracias por utilizar la aplicación PropiedadesCR.");
	         
	         Transport.send(message);

	        

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}

}
