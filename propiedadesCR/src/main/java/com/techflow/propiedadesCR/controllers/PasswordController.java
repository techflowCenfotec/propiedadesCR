
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

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.PasswordRequest;

@RestController
@RequestMapping(value="rest/protected/password")
public class PasswordController {
	
	/**
	 * Este método envía el correo.
	 * @param pmailInformation Encapsula la información del correo.
	 * @exception IOException Esta excepción se lanza cuando ocurre un error al enviar el correo.
	 */
	
	@RequestMapping(value="/sendEmail", method = RequestMethod.POST)
	public void sendEmail(@RequestBody PasswordRequest pmailInformation){
		  System.out.println(pmailInformation);
		 
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

	         
	         message.setSubject("Información de evento");
	         
	         message.setContent("<h3>Cambio de contraseña</h3>"+
	        		"<p>Estimado usuario se nos ha notificado que se ha pedido hacer un cambio de contraseña</p>"+
	        		"<p>de ser así por favor hacer clic en Cambiar contraseña, de no ser así omita el mensaje.</p>"+
	        		"<p>Un cordial saludo, PropiedadesCR</p>"+
	        		"<a href='http://localhost:8080/propiedadesCR/#/login' class='btn btn-link'>Cambiar contrase&ntilde;a</a>","text/html");
	         
	         Transport.send(message);

	        

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}

