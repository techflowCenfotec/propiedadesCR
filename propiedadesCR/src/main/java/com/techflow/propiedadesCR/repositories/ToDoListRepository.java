package com.techflow.propiedadesCR.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.TToDoList;
/**
* <h1>Repositorio de to-do list de los usuarios</h1>
* Esta clase se encarga de la comunicación con la base de datos
*
* @author  Jimmi Vila
* @version 1.0
* @since 22/02/2016
*/
public interface ToDoListRepository extends CrudRepository<TToDoList,Integer>{
	/**
	 * Método que se encarga de realizar una consulta de todos los to-do list de los usuarios
	 * que existen en la base de datos
	 */
	List<TToDoList> findAll();
	TToDoList getByIdToDoList(int id);
}
