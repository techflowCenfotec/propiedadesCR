package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.EventPOJO;

public class EventsRequest extends BaseRequest {
	
	EventPOJO event;
	
	public EventsRequest() {
		super();
	}

	public EventPOJO getEvent() {
		return event;
	}

	public void setEvent(EventPOJO pevent) {
		this.event = pevent;
	}
	
	@Override
	public String toString() {
		return "EventsRequest [event=" + event + "]";
	}
}