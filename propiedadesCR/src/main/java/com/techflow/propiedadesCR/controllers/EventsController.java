
package com.techflow.propiedadesCR.controllers;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
	
	
	@Autowired private ServletContext servletContext;
	@Autowired private EventsServiceInterface eventsService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public EventsResponse getAll(@RequestBody EventsRequest er) {
		
		
		EventsResponse response = new EventsResponse();
		response.setCode(200);
		response.setCodeMessage("Events fetch successful");
		response.setEvents(eventsService.getAll(er));
		
		return response;
	}

	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public EventsResponse create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("description")String description,
			@RequestParam("start_date")Date startDate,
			@RequestParam("id_user")int idUser){
		
		EventsResponse rs = new EventsResponse();
		String resultFileName = Utils.writeToFile(file, servletContext);
		
		if(!resultFileName.equals("")){
			EventPOJO  event = new EventPOJO();
			event.setName(name);
			event.setDescription(description);
			event.setStartDate(startDate);
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
 



   
