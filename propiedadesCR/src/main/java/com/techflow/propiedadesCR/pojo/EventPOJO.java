package com.techflow.propiedadesCR.pojo;

import java.util.Date;

public class EventPOJO {
	private int idEvent;
	private String name;
	private String description;
	private Date startDate;
	private byte active;
	private String eventImage;
	
	
	public EventPOJO(){
		super();
	}
	public int getIdEvent(){
		return idEvent;
	}
	public void setIdEvent(int pidEvent){
		this.idEvent= pidEvent;
	}
	public String getName(){
		return name;
	}
	public void setName(String pname){
		this.name = pname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String pdescription){
		this.description = pdescription;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte active) {
		this.active = active;
	}
	public String getEventImage() {
		return eventImage;
	}
	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}
	
	
}
