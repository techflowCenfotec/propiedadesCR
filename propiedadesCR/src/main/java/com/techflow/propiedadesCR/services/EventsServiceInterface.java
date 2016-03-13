package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.ejb.Tevent;
import com.techflow.propiedadesCR.pojo.EventPOJO;

public interface EventsServiceInterface {
	List<EventPOJO> getAll(EventsRequest peventRequest);
	EventPOJO getById(int pid);
	Tevent saveEvent(EventsRequest peventRequest);
	Boolean deleteEvent(int pid);
}
