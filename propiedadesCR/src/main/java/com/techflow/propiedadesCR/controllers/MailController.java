/**
 * <h1>Controlador de correo</h1>
 * Esta clase es  encargada de recibir la información entre el BackEnd y el FrondEnd
 * 
 * @author María Jesús Gutiérrez Calvo.
 * @version 1.0
 * @since 12/03/2016
 */
package com.techflow.propiedadesCR.controllers;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.MailRequest;
import com.techflow.propiedadesCR.pojo.EventPOJO;

import java.util.Properties;




@RestController
@RequestMapping(value="rest/protected/email")
public class MailController {
	
	/**
	 * Este método envía el correo.
	 * @param pmailInformation Encapsula la información del correo.
	 * @exception IOException Esta excepción se lanza cuando ocurre un error al enviar el correo.
	 */
	
	@RequestMapping(value="/sendEmail", method = RequestMethod.POST)
	public void sendEmail(@RequestBody MailRequest pmailInformation){
		  
		  EventPOJO event = pmailInformation.getEventP();
		  
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

	         
	         message.setText("Gracias por utilizar PropiedadesCR, \n"
	         		+ "\n A continuación encontrará la información del evento"
	         		+ "\n Lugar: "+"por definir"
	         		+ "\n Fecha:"+ event.getStartDate().toString()
	         		//+ "\n Hora: "+ "por definir"
	         		+ "\n Descripción del evento:"+ event.getDescription()
	         		+ "\n Gracias por utilizar la aplicación PropiedadesCR");

	         
	         Transport.send(message);

	        

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}

