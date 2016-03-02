/**
 * <h1>EventPojo</h1>
 * Enfatiza el uso de la clase simple "evento".
 * 
 * @author María Jesús Gutiérrez Calvo.
 * @version 1.0
 * @since 25/02/2016
 */

package com.techflow.propiedadesCR.pojo;

import java.util.Date;

public class EventPOJO {
	/**
	 * identificador del evento
	 */
	private int idEvent;
	/**
	 * nombre del evento
	 */
	private String name;
	/**
	 * descripcion del evento
	 */
	private String description;
	/**
	 * fecha del evento
	 */
	private Date startDate;
	/**
	 * estado del evento
	 */
	private byte active;
	/**
	 * imagen del evento
	 */
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
	public void setStartDate(Date pstartDate) {
		this.startDate = pstartDate;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte pactive) {
		this.active = pactive;
	}
	public String getEventImage() {
		return eventImage;
	}
	public void setEventImage(String peventImage) {
		this.eventImage = peventImage;
	}
	
	
}
