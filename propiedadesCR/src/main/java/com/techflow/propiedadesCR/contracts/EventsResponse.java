package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.EventPOJO;

public class EventsResponse extends BaseResponse {

	private List<EventPOJO> events;
	private EventPOJO eventP;
	
	public EventsResponse() {
		super();
	}

	public List<EventPOJO> getEvents() {
		return events;
	}

	public void setEvents(List<EventPOJO> pevents) {
		this.events = pevents;
	}
	
	public EventPOJO getEvent(){
		return eventP;
	}
	
	public void setEvent(EventPOJO peventP){
		this.eventP= peventP;
	}
	
	
}

