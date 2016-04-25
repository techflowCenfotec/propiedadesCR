/**
 * <h1>Servicio de los eventos</h1>
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.contracts.EventsResponse;
import com.techflow.propiedadesCR.ejb.Tevent;
import com.techflow.propiedadesCR.ejb.Tuser;
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
	 * Este método trae una lista de todos los eventos registrados en la base de datos.
	 * @param peventRequest Encapsula la información solicitada por el usuario.
	 * @return eventsResponse Se retorna la respuesta del BackEnd al FrondEnd
	 */
	public EventsResponse getAll(EventsRequest peventRequest) {
		EventsResponse eventResponse = new EventsResponse();
		Page<Tevent> events = eventsRepository.findAll(new PageRequest(peventRequest.getPageNumber(),peventRequest.getPageSize()));
		eventResponse.setEvents(generateEventDtos(events.getContent()));
		eventResponse.setTotalPages(events.getTotalPages());
		eventResponse.setCode(200);
		return eventResponse;
	}
	/**
	 * 
	 * Este método trae una lista de todos los eventos registrados en la base de datos.
	 * @param peventRequest Encapsula la información solicitada por el usuario.
	 * @return List<EventPOJO> Se retorna la lista de eventos.
	 */
	public List<EventPOJO> getAllEvents(EventsRequest peventRequest) {
		
		List<Tevent> events = eventsRepository.findAll();
		
		return generateEventDtos(events);
	}
	
	/**
	 * 
	 * Este método genera los objetos POJO que se retornan a la IU.
	 * @param pevents Trae la lista de la tabla eventos del ejb.
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
	 * Este método registra el evento en el sistema
	 * @param peventRequest Encapsula la información solicitada por el usuario.
	 * @return nEvent Retorna el evento creado.
	 */
	public Tevent saveEvent(EventsRequest peventRequest, int pidUser) {
		
	Tevent event = new Tevent();
	Tuser user = new Tuser();
	user.setIdUser(pidUser);
	BeanUtils.copyProperties(peventRequest.getEvent(), event);
	event.setTuser(user);
	Tevent nEvent=  eventsRepository.save(event);
	
		return nEvent;
	}
	
	@Override
	@Transactional
	/**
	 * Este método trae el evento por medio del id recibido.
	 * @param pid Id del evento.
	 * @return eventPOJO Retorna un evento POJO.
	 */
	public EventPOJO getById(int pid){
		EventPOJO eventPOJO = null;
	    Tevent nevent = eventsRepository.findByIdEvent(pid);
	    if(null!=nevent){
	    	eventPOJO = new EventPOJO();
	    	BeanUtils.copyProperties(nevent, eventPOJO);
	    }
		return eventPOJO;
	}
	
	@Override
	/*
	 * Este método elimina el evento por medio del id recibido.
	 * @param pid Id del evento
	 * @return true Si la acción se ejecutó se retorna true.
	 * @return false Si la acción no se pudo ejecutar retorna un false.
	 */
	public Boolean deleteEvent(int pid) {
		if(	eventsRepository.exists(pid)){	
			eventsRepository.delete(pid);
			return true;
		}		
		return false;
	}
	
	/*
	 * Este método modifica el evento por medio del id  recibido.
	 * @param pidUser Id del usuario.
	 * @return nEvent retorna el evento modificado.
	 */
	@Override
	public Tevent modifyEvent(EventsRequest peventRequest, int pidUser) {
		
		Tevent event = new Tevent();
		Tuser user = new Tuser();
		user.setIdUser(pidUser);
		BeanUtils.copyProperties(peventRequest.getEvent(), event);
		event.setTuser(user);
		Tevent nEvent = eventsRepository.save(event);
		
		return nEvent;
	
	}
	@Override
	public List<EventPOJO> getAllEventsByUser(int pidUser) {
		ArrayList<EventPOJO> events = new ArrayList<>();
		eventsRepository.findAll().stream().forEach(event->{
			if(event.getTuser().getIdUser() ==pidUser){
				EventPOJO nevent = new EventPOJO();
				BeanUtils.copyProperties(event, nevent);
				events.add(nevent);
			}
			
		});
		return events;
	}

}
