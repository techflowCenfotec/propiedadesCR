package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.EventPOJO;

public class MailRequest extends BaseRequest{
	
	EventPOJO eventP;
	String userEmail;
	
	public MailRequest(){
		super();
	}

	public EventPOJO getEventP() {
		return eventP;
	}

	public void setEventP(EventPOJO eventP) {
		this.eventP = eventP;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "MailRequest [eventP=" + eventP + ", userEmail=" + userEmail + "]";
	}
	

	
	
	
	
	
	
	
}
