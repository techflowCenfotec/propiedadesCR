package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.pojo.EventPOJO;

public interface EventsServiceInterface {
	List<EventPOJO> getAll(EventsRequest event);
	Boolean saveEvent(EventsRequest event);
}
