package com.techflow.propiedadesCR.pojo;

public class EventPOJO {
	private int idEvent;
	private String name;
	private String description;
	private String startDate;
	
	
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
	public String getDEscription(){
		return description;
	}
	public void setDescription(String pdescription){
		this.description = pdescription;
	}
}
