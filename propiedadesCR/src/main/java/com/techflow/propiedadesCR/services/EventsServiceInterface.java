package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.contracts.EventsResponse;
import com.techflow.propiedadesCR.ejb.Tevent;
import com.techflow.propiedadesCR.pojo.EventPOJO;

public interface EventsServiceInterface {
	EventsResponse getAll(EventsRequest peventRequest);
	List<EventPOJO> getAllEvents(EventsRequest peventRequest);
	EventPOJO getById(int pid);
	Tevent saveEvent(EventsRequest peventRequest,int pidUser);
	Tevent modifyEvent(EventsRequest peventRequest, int pidUser);
	Boolean deleteEvent(int pid);
}
