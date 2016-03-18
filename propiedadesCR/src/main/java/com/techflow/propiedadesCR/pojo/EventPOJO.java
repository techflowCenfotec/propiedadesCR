/**
 * <h1>POJO del evento</h1>
 * Esta clase enfatiza el uso de la clase simple "evento".
 * 
 * @author María Jesús Gutiérrez Calvo.
 * @version 1.0
 * @since 25/02/2016
 */

package com.techflow.propiedadesCR.pojo;

import java.util.Date;



public class EventPOJO {
	/**
	 * Identificador del evento
	 */
	private int idEvent;
	/**
	 * Nombre del evento
	 */
	private String name;
	/**
	 * Descripción del evento
	 */
	private String description;
	/**
	 * Fecha del evento
	 */
	private Date startDate;
	/**
	 * Estado del evento
	 */
	private byte active;
	/**
	 * Ruta de la imagen del evento
	 */
	private String eventImage;
	/**
	 * Dirección del evento
	 */
	private String address;
	
	/**
	 * Locación del evento.
	 */
	private String coordinates;
	
	
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String paddress) {
		this.address = paddress;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	
	
}
