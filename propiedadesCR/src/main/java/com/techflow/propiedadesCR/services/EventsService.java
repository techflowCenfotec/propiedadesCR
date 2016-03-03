/**
 * <h1>Servicio de los bancos</h1>
 * Esta clase es la encargada de ofrecer los servicios 
 * y administrar las transacciones al repositorio.
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
	 * Objeto que se comunica con la base de datos.
	 */
	@Autowired private EventsRepository eventsRepository;
	
	@Override
	@Transactional
	/**
	 * 
	 * getAll Trae una lista de todos los eventos registrados en la base de datos.
	 * @param EventsRequest Encapsula la información solicitada por el usuario.
	 * @return generateEventDtos Genera los objetos POJO que se retornan a la IU. 
	 */
	public List<EventPOJO> getAll(EventsRequest peventRequest) {
		List<Tevent> events = eventsRepository.findAll();
		return generateEventDtos(events);
	}
	/**
	 * 
	 * generateEventDtos Genera los objetos POJO que se retornan a la IU.
	 * @param events Trae la lista de la tabla eventos del ejb.
	 * @return uiEvents Lista de los eventos del sistema.
	 */
	private List<EventPOJO> generateEventDtos(List<Tevent> pevents) {
		List<EventPOJO> uiEvents = new ArrayList<EventPOJO>();
		pevents.stream().forEach(u -> {
			EventPOJO dto = new EventPOJO();
			BeanUtils.copyProperties(u, dto);
			uiEvents.add(dto);
		});
		return uiEvents;
	}

	@Override
	@Transactional
	/**
	 * 
	 * saveEvent Registra el evento en el sistema
	 * @param EventRequest Encapsula la información solicitada por el usuario.
	 * @return nEvent Retorna el evento creado.
	 */
	public Tevent saveEvent(EventsRequest peventRequest) {
		
	Tevent event = new Tevent();
	BeanUtils.copyProperties(peventRequest.getEvent(), event);
	Tevent nEvent=  eventsRepository.save(event);
	
		//return (nEvent == null) ? false : true;
		return nEvent;
	}

}
