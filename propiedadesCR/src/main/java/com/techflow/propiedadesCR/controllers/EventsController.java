/**
 * <h1>EventsController</h1>
 * Descripción de la clase: 
 * Esta clase es  encargada de recibir la información entre el BackEnd y el FrondEnd
 * 
 * @author María Jesús Gutiérrez Calvo.
 * @version 1.0
 * @since 25/02/2016
 */

package com.techflow.propiedadesCR.controllers;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.contracts.EventsResponse;
import com.techflow.propiedadesCR.ejb.Tevent;
import com.techflow.propiedadesCR.pojo.EventPOJO;
import com.techflow.propiedadesCR.services.EventsServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

@RestController
@RequestMapping(value="rest/protected/events")

public class EventsController {
	
	/**
	 * servletContext, autowired utilizado para el manejo de archivos.
	 */
	@Autowired private ServletContext servletContext;
	/**
	 * eventsService, autowired utilizado para los servicios de los eventos.
	 */
	@Autowired private EventsServiceInterface eventsService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	
	/**
	 * Descripción de lo que hace la función:
	 * Método getAll, trae todos los eventos registrados.
	 * @param EventRequest, encapsula la información solicitada por el usuario.
	 * @return response, se retorna la respuesta del BackEnd al FrondEnd.
	 */
	public EventsResponse getAll(@RequestBody EventsRequest er) {
		
		
		EventsResponse response = new EventsResponse();
		response.setCode(200);
		response.setCodeMessage("Events fetch successful");
		response.setEvents(eventsService.getAll(er));
		
		return response;
	}

	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	/**
	 * Descripción de la función: 
	 * Método create, envía los datos a la base de datos para registrar el evento.
	 * @param file, se recibe el archivo subido por el usuario.
	 * @param name, se recibe el nombre del evento a registrar.
	 * @param description, se recibe la descripción del evento a registrar.
	 * @param startDate, se recibe la fecha del evento a registrar.
	 * @param idUser, se recibe el identificador del usuario que registra el evento.
	 * @return EventResponse retorna la respuesta del BackEnd al FrondEnd
	 * @exception ParseException(esta exepción se lanza cuando la fecha no lleva el formato correcto)
	 */
	public EventsResponse create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("description")String description,
			@RequestParam("start_date")String startDate,
			@RequestParam("id_user")int idUser){
		 
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventsResponse rs = new EventsResponse();
		String resultFileName = Utils.writeToFile(file, servletContext);
		
		
		if(!resultFileName.equals("")){
			EventPOJO  event = new EventPOJO();
			event.setName(name);
			event.setDescription(description);
			event.setStartDate(date);
			event.setEventImage(resultFileName);
			event.setActive((byte)1);
			EventsRequest er = new EventsRequest();
			er.setEvent(event);
			Tevent recentlyCreatedEvent = new Tevent();
			recentlyCreatedEvent = eventsService.saveEvent(er);
		if(recentlyCreatedEvent!=null){
			rs.setCode(200);
			rs.setCodeMessage("Events created successful");
		  }
	    }else{
	    	rs.setCode(409);
	    	rs.setErrorMessage("create/edit conflict");
	    }
		
		return rs;
		
    }
  }
 



   
