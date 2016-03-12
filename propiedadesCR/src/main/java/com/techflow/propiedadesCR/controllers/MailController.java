package com.techflow.propiedadesCR.controllers;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.EventsRequest;

import java.util.Properties;

import javax.activation.*;

@RestController
@RequestMapping(value="rest/protected/email")
public class MailController {
	
	
	@RequestMapping(value="/sendEmail", method = RequestMethod.GET)
	public void sendEmail(){
	
		// Recipient's email ID needs to be mentioned.
	      String to = "m.gutierrez2030@gmail.com";//change accordingly

	      // Sender's email ID needs to be mentioned
	      String from = "propiedadescr.tech@gmail.com";//change accordingly
	      final String username = "propiedadescr.tech@gmail.com";//change accordingly
	      final String password = "mjjvwTechflow";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Información de evento");

	         // Now set the actual message
	         message.setText("Buenas, "
	         		+ "En el presente correo encontrará la información del evento"
	         		+ "Lugar: "
	         		+ "Fecha:"
	         		+ "Hora:"
	         		+ "Descripción del evento:"
	         		+ "Gracias por utilizar la aplicación PropiedadesCR");

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}
class GMailAuthenticator extends Authenticator {
    String user;
    String pw;
    public GMailAuthenticator (String username, String password)
    {
       super();
       this.user = username;
       this.pw = password;
    }
   public PasswordAuthentication getPasswordAuthentication()
   {
      return new PasswordAuthentication(user, pw);
   }
}