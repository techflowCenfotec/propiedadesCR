/**
 * <h1>Controlador de eventos</h1>
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
	 * Objeto usado para el manejo de archivos.
	 */
	
	@Autowired private ServletContext servletContext;
	/**
	 *  Objeto que ofrece los servicios de los eventos.
	 */
	
	@Autowired private EventsServiceInterface eventsService;
	
	/**
	 * Método getAll Trae todos los eventos registrados.
	 * @param peventRequest Encapsula la información solicitada por el usuario.
	 * @return response Se retorna la respuesta del BackEnd al FrondEnd.
	 */
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	
	
	public EventsResponse getAll(@RequestBody EventsRequest peventRequest) {
		
		
		EventsResponse response = new EventsResponse();
		response.setCode(200);
		response.setCodeMessage("Events fetch successful");
		response.setEvents(eventsService.getAll(peventRequest));
		
		return response;
	}
	/**
	 * 
	 * Método create Envía los datos a la base de datos para registrar el evento.
	 * @param file Se recibe el archivo subido por el usuario.
	 * @param name Se recibe el nombre del evento a registrar.
	 * @param description Se recibe la descripción del evento a registrar.
	 * @param startDate Se recibe la fecha del evento a registrar.
	 * @param idUser Se recibe el identificador del usuario que registra el evento.
	 * @return response Retorna la respuesta del BackEnd al FrondEnd
	 * @exception ParseException(esta exepción se lanza cuando la fecha no lleva el formato correcto)
	 */
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	
	public EventsResponse create(
			@RequestParam("file") MultipartFile pfile,
			@RequestParam("name") String pname,
			@RequestParam("description")String pdescription,
			@RequestParam("start_date")String pstartDate,
			@RequestParam("id_user")int pidUser){
		 
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pstartDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventsResponse response = new EventsResponse();
		String resultFileName = Utils.writeToFile(pfile, servletContext);
		
		
		if(!resultFileName.equals("")){
			EventPOJO  event = new EventPOJO();
			event.setName(pname);
			event.setDescription(pdescription);
			event.setStartDate(date);
			event.setEventImage(resultFileName);
			event.setActive((byte)1);
			EventsRequest eventRequest= new EventsRequest();
			eventRequest.setEvent(event);
			Tevent recentlyCreatedEvent = new Tevent();
			recentlyCreatedEvent = eventsService.saveEvent(eventRequest);
		if(recentlyCreatedEvent!=null){
			response.setCode(200);
			response.setCodeMessage("Events created successful");
		  }
	    }else{
	    	response.setCode(409);
	    	response.setErrorMessage("create/edit conflict");
	    }
		
		return response;
		
    }
  }
 



   
