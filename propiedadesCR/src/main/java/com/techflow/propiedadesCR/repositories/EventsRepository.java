/*
 * <h1>EventsRepository</h1>
 * Descripción de la clase
 * Clase encargada de la comunicación con la base de datos.
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
/**
 * Descripción de la función:
 * Busca todos los eventos de la tabla Tevent y los guarda en una lista.
 *
 */
public interface EventsRepository extends CrudRepository<Tevent, Integer>{
	
	List<Tevent> findAll();
	
}

