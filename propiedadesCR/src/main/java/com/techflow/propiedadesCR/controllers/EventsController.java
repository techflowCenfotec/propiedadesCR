/**
 * <h1>Controlador de eventos</h1>
 * Esta clase es  encargada de recibir la información entre el BackEnd y el FrondEnd
 * 
 * @author María Jesús Gutiérrez Calvo.
 * @version 1.0
 * @since 25/02/2016
 */

package com.techflow.propiedadesCR.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	 * Este método trae todos los eventos registrados.
	 * @param peventRequest Encapsula la información solicitada por el usuario.
	 * @return response Se retorna la respuesta del BackEnd al FrondEnd.
	 */
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	
	
	public EventsResponse getAll(@RequestBody EventsRequest peventRequest) {
		
		return eventsService.getAll(peventRequest);
		
	}
	
	/**
	 * Este método trae todos los eventos registrados.
	 * @param peventRequest Encapsula la información solicitada por el usuario.
	 * @return response Se retorna la respuesta del BackEnd al FrondEnd.
	 */
	@RequestMapping(value="/getAllEvents", method = RequestMethod.POST)
		
	public EventsResponse getAllEvents(@RequestBody EventsRequest peventRequest) {
			
		EventsResponse response = new EventsResponse();
		response.setCode(200);
		response.setCodeMessage("Events fetch successful");
		response.setEvents(eventsService.getAllEvents(peventRequest));
		
		return response;
	}
	/**
	 * 
	 * Este método envía los datos a la base de datos para registrar el evento.
	 * 
	 * @param pfile Se recibe el archivo subido por el usuario.
	 * @param pname Se recibe el nombre del evento a registrar.
	 * @param pdescription Se recibe la descripción del evento a registrar.
	 * @param pstartDate Se recibe la fecha del evento a registrar.
	 * @param paddress Se recibe la dirección del evento al registrar.
	 * @param pcoordinates Se recibe las coordenadas del mapa al registrar evento.
	 * @param pidUser Se recibe el identificador del usuario que registra el evento.
	 * @return response Retorna la respuesta del BackEnd al FrondEnd
	 * 
	 * @throws ParseException Esta exepción se lanza cuando el sistema es incapaz de transformar
	 * el String pstartDate a startDate que es de tipo Date.
	 */
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	
	public EventsResponse create(
			@RequestParam("file") MultipartFile pfile,
			@RequestParam("name") String pname,
			@RequestParam("description")String pdescription,
			@RequestParam("start_date")String pstartDate,
			@RequestParam("address")String paddress,
			@RequestParam("coordinates") String pcoordinates,
			@RequestParam("id_user")int pidUser){
		 
		Date startDate = new Date();
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pstartDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventsResponse response = new EventsResponse();
		
		String resultFileName;
		
		if(pfile.getOriginalFilename().equals("default_events_image_PropiedadesCR.png"))
			 resultFileName = "http://localhost:8080/propiedadesCR/resources/images/default_events_image.png";
			else			
			 resultFileName = Utils.writeToFile(pfile,servletContext);
		
		if(!resultFileName.equals("")){
			EventPOJO  event = new EventPOJO();
			event.setName(pname);
			event.setDescription(pdescription);
			event.setStartDate(startDate);
			event.setEventImage(resultFileName);
			event.setAddress(paddress);
			event.setCoordinates(pcoordinates);
		    event.setActive((byte)1);
			EventsRequest eventRequest= new EventsRequest();
			eventRequest.setEvent(event);
			Tevent recentlyCreatedEvent = new Tevent();
			recentlyCreatedEvent = eventsService.saveEvent(eventRequest,pidUser);
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
	/**
	 * Este método trae el evento por el id recibido.
	 * @param pidEvent Identificador del evento.
	 * @return response Se retorna la respuesta del Backend al Frondend.
	 */
	
	@RequestMapping(value="/getById/{pidEvent}",method = RequestMethod.GET)
	 public EventsResponse getById(@PathVariable int pidEvent){
		EventsResponse response = new EventsResponse();
		response.setCode(200);
		response.setCodeMessage("events fetch success");
		response.setEvent(eventsService.getById(pidEvent));
		return response;
	}
	/*
	 * Este método elimina el evento por medio del ide del evento recibido.
	 * @param pid Id del evento.
	 * @return response Se retorna la respuesta del BackEnd al FrondEnd.
	 */
	
	@RequestMapping(value ="/delete", method = RequestMethod.DELETE)
	public EventsResponse delete(@RequestParam("id")  int pid){
		
		EventsResponse response = new EventsResponse();
		Boolean state = eventsService.deleteEvent(pid);
		
		if(state){
			response.setCode(200);
			response.setCodeMessage("events delete success");
			
		}
		return response;
	}
	/**
	 * Este método modifica el evento recibido por medio del id
	 * @param pfile archivo subido por el usuario
	 * @param pidEvent identificador del evento
	 * @param pname nombre del evento
	 * @param pdescription descripción del evento
	 * @param pstartDate fecha del evento
	 * @param paddress dirección del evento
	 * @param pcoordinates locación del evento
	 * @param pidUser id del usuario en sesion
	 * @return
	 */
	@RequestMapping(value="/modifyEvent", method = RequestMethod.POST)
	public EventsResponse modifyEvent(@RequestParam("file") MultipartFile pfile,
			@RequestParam("idEvent") int pidEvent,
			@RequestParam("name") String pname,
			@RequestParam("description") String pdescription,
			@RequestParam("start_date") String pstartDate,
			@RequestParam("address") String paddress,
			@RequestParam("coordinates") String pcoordinates,
			@RequestParam("id_user") int pidUser){	
		
		
		
		Date startDate = new Date();
		  try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pstartDate);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EventsResponse response = new EventsResponse();
		String resultFileName;

		if(pfile.getSize()==0)
		 resultFileName = pfile.getOriginalFilename();
		else			
		 resultFileName = Utils.writeToFile(pfile,servletContext);
		
		if(!resultFileName.equals("")){
			
			EventPOJO event = new EventPOJO();
			event.setIdEvent(pidEvent);
			event.setName(pname);
			event.setDescription(pdescription);
			event.setStartDate(startDate);
			event.setEventImage(resultFileName);
			event.setAddress(paddress);
			event.setCoordinates(pcoordinates);
			event.setActive((byte)1);
			EventsRequest eventRequest = new EventsRequest();
			eventRequest.setEvent(event);
			Tevent recentlyCreatedEvent = eventsService.modifyEvent(eventRequest,pidUser);

			
			if(recentlyCreatedEvent != null){
				response.setCode(200);
				response.setCodeMessage("Event modified ");
			}
			
		}else{
			response.setCode(409);
			response.setErrorMessage("create/edit conflict");
		}
	
		return response;		
		
	}

	
	
  }
 



   
