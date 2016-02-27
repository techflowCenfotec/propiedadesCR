package com.techflow.propiedadesCR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.contracts.EventsResponse;
import com.techflow.propiedadesCR.ejb.Tevent;
import com.techflow.propiedadesCR.services.EventsServiceInterface;
import com.techflow.propiedadesCR.utils.Utils;

@RestController
@RequestMapping(value="rest/protected/events")
public class EventsController {

	@Autowired private EventsServiceInterface eventsInterface;
	
	@RequestMapping(value="/getAll", method = RequestMethod.POST)
	public EventsResponse getAll(@RequestBody EventsRequest er) {
		
		EventsResponse response = new EventsResponse();
		response.setCode(200);
		response.setCodeMessage("Events fetch successful");
		response.setEvents(eventsInterface.getAll(er));
		
		return response;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public EventsResponse create(@RequestBody EventsRequest er) {
		
		EventsResponse response = new EventsResponse();
		Boolean state = eventsInterface.saveEvent(er);
		
		if(state){
			response.setCode(200);
			response.setCodeMessage("Events created successful");
		}
		
		return response;
	}
}
