package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tquestion;
/**
* <h1>Repositorio de preguntas</h1>
* Esta clase se encarga de la comunicación con la base de datos
*
* @author  Jimmi Vila
* @version 1.0
* @since 10/03/2016
*/
public interface QuestionsRepository extends CrudRepository<Tquestion, Integer>{
	/**
	 * Método que se encarga de realizar una consulta de todas las preguntas
	 * que existen en la base de datos
	 */
	List<Tquestion> findAll();
	
}
