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

	@Autowired private EventsRepository eventsRepository;
	
	@Override
	@Transactional
	public List<EventPOJO> getAll(EventsRequest er) {
		List<Tevent> events = eventsRepository.findAll();
		return generateEventDtos(events);
	}
	
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
	public Tevent saveEvent(EventsRequest er) {
		
	Tevent event = new Tevent();
	BeanUtils.copyProperties(er.getEvent(), event);
	Tevent nEvent=  eventsRepository.save(event);
	
		//return (nEvent == null) ? false : true;
		return nEvent;
	}

}
