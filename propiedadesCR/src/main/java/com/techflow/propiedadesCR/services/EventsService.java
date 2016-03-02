/**
 * <h1>EventsService</h1>
 * Descripción de la clase: 
 * Implementa la lista de <EventPOJO> del getAll y el saveEvent del EJB.
 * 
 * @author María Jesús Gutiérrez Calvo
 * @version 1.0
 * @since 25/02/2016
 */
package com.techflow.propiedadesCR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.ejb.Tevent;
import com.techflow.propiedadesCR.pojo.EventPOJO;
import com.techflow.propiedadesCR.repositories.EventsRepository;

@Service
public class EventsService implements EventsServiceInterface {
	/*
	 * eventsRepository encargado de la comunicación con la base de datos.
	 */
	@Autowired private EventsRepository eventsRepository;
	
	@Override
	@Transactional
	/**
	 * Descripción de la función:
	 * getAll, trae una lista de todos los eventos registrados en la base de datos.
	 * @param EventsRequest, encapsula la información solicitada por el usuario.
	 * @return generateEventDtos, genera los objetos POJO que se retornan a la IU. 
	 */
	public List<EventPOJO> getAll(EventsRequest er) {
		List<Tevent> events = eventsRepository.findAll();
		return generateEventDtos(events);
	}
	/**
	 * Descripción de la función:
	 * generateEventDtos, genera los objetos POJO que se retornan a la IU.
	 * @param events, trae la lista de la tabla eventos del ejb.
	 * @return uiEvents, lista de los objetos POJO.
	 */
	private List<EventPOJO> generateEventDtos(List<Tevent> events) {
		List<EventPOJO> uiEvents = new ArrayList<EventPOJO>();
		events.stream().forEach(u -> {
			EventPOJO dto = new EventPOJO();
			BeanUtils.copyProperties(u, dto);
			uiEvents.add(dto);
		});
		return uiEvents;
	}

	@Override
	@Transactional
	/**
	 * Descripción de la función:
	 * saveEvent, registra el evento en el sistema
	 * @param EventRequest, encapsula la información solicitada por el usuario.
	 * @return nEvent, retorna el evento creado.
	 */
	public Tevent saveEvent(EventsRequest er) {
		
	Tevent event = new Tevent();
	BeanUtils.copyProperties(er.getEvent(), event);
	Tevent nEvent=  eventsRepository.save(event);
	
		//return (nEvent == null) ? false : true;
		return nEvent;
	}

}
