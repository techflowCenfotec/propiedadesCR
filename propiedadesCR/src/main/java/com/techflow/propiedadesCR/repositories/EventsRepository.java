/*
 * <h1>Repositorio de eventos</h1>
 * 
 * Esta clase se encarga de la comunicación con la base de datos.
 * 
 * @author María Jesús Gutiérrez Calvo.
 * @version 1.0
 * @since 25/02/2016
 */

package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.contracts.EventsRequest;
import com.techflow.propiedadesCR.ejb.Tevent;
import com.techflow.propiedadesCR.pojo.EventPOJO;

public interface EventsRepository extends CrudRepository<Tevent, Integer>{
	/**
	  * Este método retorna todos los eventos registrados en el sistema
	  *
	  * @return List<event> Retorna la respuesta de la BD hacia el servicio. 
	  */
	List<Tevent> findAll();
	
	/**
	 * Este método retorna el evento encontrado por el id del mismo.
	 * @param pid
	 * @return Tevent Retorna la respuesta de la BD hacia el servicio. 
	 */
	
	Tevent findByIdEvent(int pid);
	
}

